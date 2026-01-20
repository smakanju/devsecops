# Catalog Service Runbook

## Dependencies
- Aurora PostgreSQL (catalog)
- MSK Kafka
- Redis cache

## Common Issues
- DB connection saturation
- Authentication failures

## Recovery Steps
1. Validate DB connectivity.
2. Rotate credentials in Secrets Manager if needed.
3. Check Kyverno policy compliance.
