# System Architecture Pack

## Bounded Contexts & Services

| Bounded Context | Service | Data Ownership | Primary APIs | Events |
| --- | --- | --- | --- | --- |
| Catalog | catalog-service | Aurora PostgreSQL `catalog` schema | REST `/api/v1/catalog` | `catalog.item.created.v1`, `catalog.item.updated.v1`, `catalog.item.deleted.v1` |
| Orders | orders-service | Aurora PostgreSQL `orders` schema | REST `/api/v1/orders` | `order.created.v1`, `order.confirmed.v1`, `order.cancelled.v1` |
| Payments | payments-service | Aurora PostgreSQL `payments` schema | REST `/api/v1/payments` | `payment.authorized.v1`, `payment.captured.v1`, `payment.failed.v1` |
| Inventory | inventory-service | Aurora PostgreSQL `inventory` schema | REST `/api/v1/inventory` | `inventory.reserved.v1`, `inventory.released.v1` |
| Customers | customers-service | Aurora PostgreSQL `customers` schema | REST `/api/v1/customers` | `customer.created.v1`, `customer.updated.v1` |
| Shipping | shipping-service | Aurora PostgreSQL `shipping` schema | REST `/api/v1/shipping` | `shipment.created.v1`, `shipment.delivered.v1` |
| Promotions | promotions-service | Aurora PostgreSQL `promotions` schema | REST `/api/v1/promotions` | `promotion.activated.v1` |
| Notification | notification-service | Aurora PostgreSQL `notification` schema | REST `/api/v1/notifications` | `notification.sent.v1` |
| Agent Platform | agent-control-plane | S3 + DynamoDB audit ledger | REST `/api/v1/agents` | `audit.event.created.v1` |

## Event Topics (MSK)

- `catalog.item.created.v1`
- `catalog.item.updated.v1`
- `catalog.item.deleted.v1`
- `order.created.v1`
- `order.confirmed.v1`
- `payment.authorized.v1`
- `payment.captured.v1`
- `payment.failed.v1`
- `inventory.reserved.v1`
- `inventory.released.v1`
- `audit.event.created.v1`

## C4 Diagrams (Text)

### C4 Context

```
[Customer] -> (Web UI)
(Web UI) -> [API Gateway]
[API Gateway] -> (Catalog Service)
[API Gateway] -> (Orders Service)
[API Gateway] -> (Payments Service)
(Catalog Service) -> [Aurora PostgreSQL]
(Orders Service) -> [Aurora PostgreSQL]
(Payments Service) -> [Aurora PostgreSQL]
(Catalog Service) -> [MSK Kafka]
(Orders Service) -> [MSK Kafka]
(Payments Service) -> [MSK Kafka]
(Agent Platform) -> [GitHub Actions]
(Agent Platform) -> [CloudWatch Logs]
(Agent Platform) -> [S3 Evidence]
```

### C4 Container

```
[API Gateway]
  -> [Catalog Service: Spring Boot 3]
  -> [Orders Service: Spring Boot 3]
  -> [Payments Service: Spring Boot 3]

[Catalog Service] -> [Aurora PostgreSQL: catalog]
[Orders Service] -> [Aurora PostgreSQL: orders]
[Payments Service] -> [Aurora PostgreSQL: payments]

[Services] -> [MSK Kafka]
[Services] -> [Redis]

[Agent Platform: FastAPI + LangGraph]
  -> [Bedrock]
  -> [CloudWatch Logs]
  -> [S3 Audit Ledger]
```

## Sequence Diagrams (Text)

### Catalog Item Creation

```
Client -> API Gateway: POST /api/v1/catalog
API Gateway -> Catalog Service: Create item
Catalog Service -> Aurora PostgreSQL: Insert catalog item
Catalog Service -> MSK Kafka: Publish catalog.item.created.v1
Catalog Service -> Client: 201 Created + payload
```

### Order Checkout (Future Phase)

```
Client -> API Gateway: POST /api/v1/orders
Orders Service -> Payments Service: Authorize payment
Payments Service -> MSK Kafka: payment.authorized.v1
Orders Service -> Inventory Service: Reserve inventory
Inventory Service -> MSK Kafka: inventory.reserved.v1
Orders Service -> MSK Kafka: order.confirmed.v1
Orders Service -> Client: 201 Created + order
```
