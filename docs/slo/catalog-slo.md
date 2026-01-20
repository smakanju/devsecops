# Catalog Service SLOs

## Availability
- **Target:** 99.9% successful responses per 30-day window.

## Latency
- **Target:** 95th percentile < 250ms for GET /api/v1/catalog.

## Error Budget Policy
- Freeze deployments if error budget burn exceeds 2% in 7 days.
