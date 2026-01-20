# Agent Control Plane

## PR Review Workflow

The PR review workflow uses LangGraph to coordinate agents and produce a decision ledger containing architecture, security, and testing findings.

### Endpoints
- `POST /api/v1/reviews`

### Decision Ledger
- Architecture findings
- Security findings
- Test plan coverage
- Pass/Fail recommendation
- Evidence artifact links
