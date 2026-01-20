# Evidence Pack Format

## Evidence Sources
- CI/CD logs (GitHub Actions)
- Image signing logs (Cosign)
- Policy enforcement (Kyverno reports)
- Audit ledger (CloudWatch + S3 archive)
- Test and scan reports (CodeQL, Trivy, Checkov)

## Storage Locations
- **CloudWatch Logs:** Real-time audit events and agent actions.
- **S3 Evidence Bucket:** Immutable archive of scan artifacts and decision ledgers.
- **Git Repository:** ADRs, runbooks, and threat models.

## Why We Store It
- Support compliance audits (SOC2, ISO 27001).
- Provide traceability for deployments and changes.
- Enable incident post-mortems.
