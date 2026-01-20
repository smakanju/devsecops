from __future__ import annotations

from typing import Any, Dict


class GitHubPullRequestClient:
    def fetch_diff(self, repo: str, pr_number: int) -> str:
        return f"diff for {repo}#{pr_number}"

    def comment(self, repo: str, pr_number: int, body: str) -> None:
        print(f"commented on {repo}#{pr_number}: {body}")


class BedrockClient:
    def invoke_model(self, model_id: str, prompt: str) -> Dict[str, Any]:
        return {"model_id": model_id, "output": "stubbed response", "prompt": prompt}


class KubectlReadOnly:
    def get(self, resource: str, namespace: str) -> str:
        return f"kubectl get {resource} -n {namespace}"


class ScanTriggerClient:
    def trigger(self, scan_type: str, target: str) -> Dict[str, str]:
        return {"scan_type": scan_type, "target": target, "status": "queued"}
