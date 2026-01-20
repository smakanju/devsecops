# EKS Module

Provision an Amazon EKS cluster, managed node groups, and required addons.

Inputs:
- `cluster_name`
- `vpc_id`
- `private_subnet_ids`

Outputs:
- `cluster_endpoint`
- `cluster_oidc_issuer_url`
