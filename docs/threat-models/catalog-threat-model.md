# Catalog Service Threat Model

## Scope
- Service: catalog-service
- Data store: Aurora PostgreSQL (catalog)
- External dependencies: MSK Kafka, Redis, AWS Secrets Manager

## Assets
- Product catalog data (SKU, pricing)
- Service credentials and API keys

## Threats (STRIDE)
- **Spoofing:** Unauthorized API access to update catalog items.
- **Tampering:** Data tampering in transit between API and database.
- **Repudiation:** Lack of audit trail for item changes.
- **Information Disclosure:** Exposure of pricing via unsecured endpoints.
- **Denial of Service:** Excessive requests to catalog endpoints.
- **Elevation of Privilege:** Misconfigured RBAC allowing write access.

## Mitigations
- Enforce authentication and role-based access (CATALOG_ADMIN vs CATALOG_READ).
- mTLS via service mesh and TLS to Aurora.
- Emit audit events and store in CloudWatch + S3.
- Rate limits at API Gateway and WAF.
- Kyverno policies to enforce non-root and signed images.

## Residual Risks
- Traffic spikes can still impact availability; mitigate with autoscaling and caching.
