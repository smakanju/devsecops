from __future__ import annotations

from datetime import datetime, timezone
from typing import Any, Dict
from pydantic import BaseModel, Field


class AuditEvent(BaseModel):
    actor: str
    action: str
    why: str
    input: Dict[str, Any]
    output: Dict[str, Any]
    tools: list[str] = Field(default_factory=list)
    timestamp: datetime = Field(default_factory=lambda: datetime.now(timezone.utc))
    correlation_id: str


class AuditSink:
    def emit(self, event: AuditEvent) -> None:
        raise NotImplementedError


class CloudWatchSink(AuditSink):
    def emit(self, event: AuditEvent) -> None:
        payload = event.model_dump()
        print(f"cloudwatch_event={payload}")


class S3ArchiveSink(AuditSink):
    def emit(self, event: AuditEvent) -> None:
        payload = event.model_dump()
        print(f"s3_archive_event={payload}")
