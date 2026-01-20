# Incident Response Runbook

## Detection
- Alerts from CloudWatch, Prometheus, or SLO breach notifications.

## Triage
1. Identify affected services and blast radius.
2. Check recent deployments via Argo CD.
3. Review logs/traces with correlation IDs.

## Mitigation
- Roll back via Argo CD if deployment issue.
- Scale up pods or adjust HPA limits.
- Engage on-call SRE/Incident Agent for root cause.

## Post-Incident
- Create RCA and update evidence pack.
- Open remediation PRs.
