# Decision Ledger Format

```json
{
  "pr_number": 123,
  "findings": {
    "architecture": ["Bounded contexts validated."],
    "security": ["Kyverno policies enforced."],
    "tests": ["Integration tests executed."]
  },
  "recommendation": "pass",
  "artifacts": [
    "s3://evidence-bucket/reviews/pr-123",
    "cloudwatch://audit/agent-control-plane"
  ]
}
```

The ledger is generated for every PR review workflow and stored in the evidence archive.
