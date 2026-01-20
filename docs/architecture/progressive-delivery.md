# Progressive Delivery Plan

## Strategy
- Use Argo Rollouts for canary and blue/green deployments.
- Canary steps: 10% -> 25% -> 50% -> 100% with automated analysis.
- Rollback triggers: error rate > 2%, latency p95 > 250ms.

## Gate Inputs
- SLO burn rate alerts
- Trivy + CodeQL scan results
- Kyverno policy compliance
