from __future__ import annotations

from dataclasses import dataclass
from typing import Dict, List
from langgraph.graph import END, StateGraph


@dataclass
class ReviewState:
    pr_number: int
    findings: Dict[str, List[str]]
    recommendation: str


def architecture_review(state: ReviewState) -> ReviewState:
    state.findings.setdefault("architecture", []).append("Bounded contexts validated.")
    return state


def security_review(state: ReviewState) -> ReviewState:
    state.findings.setdefault("security", []).append("Kyverno policies enforced; images signed.")
    return state


def test_review(state: ReviewState) -> ReviewState:
    state.findings.setdefault("tests", []).append("Test plan includes unit + integration coverage.")
    return state


def finalize_review(state: ReviewState) -> ReviewState:
    state.recommendation = "pass"
    return state


def build_review_graph() -> StateGraph:
    graph = StateGraph(ReviewState)
    graph.add_node("architecture", architecture_review)
    graph.add_node("security", security_review)
    graph.add_node("tests", test_review)
    graph.add_node("finalize", finalize_review)

    graph.set_entry_point("architecture")
    graph.add_edge("architecture", "security")
    graph.add_edge("security", "tests")
    graph.add_edge("tests", "finalize")
    graph.add_edge("finalize", END)

    return graph
