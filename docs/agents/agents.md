# Agent Team Definition

## Domain Architect Agent
- **Responsibilities:** Define bounded contexts, service boundaries, and event contracts.
- **Inputs:** Business requirements, existing ADRs, contract specs.
- **Outputs:** Updated architecture docs, service list, event topic definitions.
- **Permissions:** Read-only access to contracts and docs.

## Threat Modeling Agent
- **Responsibilities:** Build threat models and security assumptions.
- **Inputs:** Architecture docs, data flow diagrams.
- **Outputs:** Threat model artifacts and mitigations.
- **Permissions:** Read-only access to docs and security policies.

## Implementation Agent
- **Responsibilities:** Generate safe scaffolds and code changes.
- **Inputs:** Requirements, contract specs, ADRs.
- **Outputs:** Code changes, manifests, tests.
- **Permissions:** Write access to services and platform directories.

## Code Review Agent
- **Responsibilities:** Review PRs for architecture compliance and code quality.
- **Inputs:** PR diff, test outputs.
- **Outputs:** Review feedback and approval/reject decision.
- **Permissions:** Read-only access to repo and CI logs.

## Security Scan Agent
- **Responsibilities:** Trigger and evaluate SAST, SCA, image scans.
- **Inputs:** Scan results, SBOMs.
- **Outputs:** Security findings and remediation suggestions.
- **Permissions:** Read-only access to scan artifacts.

## Test Architect Agent
- **Responsibilities:** Define test strategy and coverage expectations.
- **Inputs:** Test results, coverage reports.
- **Outputs:** Test plan and gaps.
- **Permissions:** Read-only access to tests and CI output.

## Release Manager Agent
- **Responsibilities:** Coordinate release readiness, versioning, and deployment gates.
- **Inputs:** CI/CD status, change logs.
- **Outputs:** Release decision and promotion plan.
- **Permissions:** Read-only access to GitOps config.

## SRE/Incident Agent
- **Responsibilities:** Monitor SLOs and triage incidents.
- **Inputs:** Logs, traces, metrics.
- **Outputs:** Incident tickets and remediation PRs.
- **Permissions:** Read-only access to observability tools.

## Compliance/Evidence Agent
- **Responsibilities:** Capture audit evidence and compliance artifacts.
- **Inputs:** Audit events, policy reports.
- **Outputs:** Evidence pack updates.
- **Permissions:** Write access to evidence store metadata.
