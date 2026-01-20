from __future__ import annotations

from fastapi import FastAPI
from pydantic import BaseModel, Field
from app.audit import AuditEvent, CloudWatchSink, S3ArchiveSink
from app.workflow import ReviewState, build_review_graph

app = FastAPI(title="Agent Control Plane")
cloudwatch = CloudWatchSink()
s3_archive = S3ArchiveSink()


class ReviewRequest(BaseModel):
    pr_number: int
    actor: str
    repository: str
    reason: str


class DecisionLedger(BaseModel):
    pr_number: int
    findings: dict[str, list[str]]
    recommendation: str
    artifacts: list[str] = Field(default_factory=list)


@app.post("/api/v1/reviews", response_model=DecisionLedger)
async def run_review(request: ReviewRequest) -> DecisionLedger:
    graph = build_review_graph().compile()
    initial = ReviewState(pr_number=request.pr_number, findings={}, recommendation="pending")
    result = graph.invoke(initial)

    ledger = DecisionLedger(
        pr_number=request.pr_number,
        findings=result.findings,
        recommendation=result.recommendation,
        artifacts=[
            "s3://evidence-bucket/reviews/pr-{}".format(request.pr_number),
            "cloudwatch://audit/agent-control-plane",
        ],
    )

    event = AuditEvent(
        actor=request.actor,
        action="pr_review",
        why=request.reason,
        input=request.model_dump(),
        output=ledger.model_dump(),
        tools=["langgraph", "cloudwatch", "s3"],
        correlation_id=f"pr-{request.pr_number}",
    )

    cloudwatch.emit(event)
    s3_archive.emit(event)

    return ledger


def main() -> None:
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=9000)
