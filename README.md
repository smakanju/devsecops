
# DevSecOps E-Commerce Platform (AWS + Java + GitOps)

This repository contains an enterprise-grade e-commerce microservices platform built for AWS with Java 21, Spring Boot 3, EKS, GitOps, and an AI agent control plane.

## Repository Structure

- `services/` Spring Boot microservices (catalog is the reference implementation).
- `agent-platform/` FastAPI + LangGraph AI agent control plane.
- `platform/terraform/` AWS infrastructure modules and environment stacks.
- `platform/gitops/` Argo CD app-of-apps and Kustomize overlays.
- `contracts/` OpenAPI + AsyncAPI contracts.
- `docs/` Architecture, ADRs, runbooks, SLOs, and threat models.

## Quick Start (Catalog Service)

```bash
mvn -f services/catalog/pom.xml spring-boot:run
```

OpenAPI UI is available at `http://localhost:8080/swagger-ui/index.html`.
