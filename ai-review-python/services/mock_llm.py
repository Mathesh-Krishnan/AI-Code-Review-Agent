from models.schemas import ReviewRequest, ReviewResponse, CodeIssue
from services.llm_interface import LLMInterface


class MockLLM(LLMInterface):
    def review_code(self, request: ReviewRequest) -> ReviewResponse:
        issues = []

        for signal in request.signals:
            if signal.name == "HARDCODED_SECRET":
                issues.append(CodeIssue(
                    type="SECURITY",
                    description=signal.description,
                    suggestion="Move secrets to environment variables or a vault"
                ))
            elif signal.name == "TIGHT_COUPLING":
                issues.append(CodeIssue(
                    type="DESIGN",
                    description=signal.description,
                    suggestion="Introduce dependency injection to reduce coupling"
                ))
            elif signal.name == "LOOP_USAGE":
                issues.append(CodeIssue(
                    type="PERFORMANCE",
                    description=signal.description,
                    suggestion="Review loop complexity and optimize if needed"
                ))
            elif signal.name == "LARGE_METHOD":
                issues.append(CodeIssue(
                    type="READABILITY",
                    description=signal.description,
                    suggestion="Break large methods into smaller units"
                ))

        summary = (
            "Code looks clean with no major issues detected."
            if not issues
            else "Agent analyzed the code, prioritized risks, and generated actionable recommendations."
        )

        return ReviewResponse(summary=summary, issues=issues)

