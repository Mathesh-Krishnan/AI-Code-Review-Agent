import google.generativeai as genai
import os
import json
from dotenv import load_dotenv
from models.schemas import ReviewRequest, ReviewResponse, CodeIssue
from services.llm_interface import LLMInterface

load_dotenv()

class GeminiLLM(LLMInterface):
    def __init__(self):
        genai.configure(api_key=os.getenv("GEMINI_API_KEY"))
        self.model = genai.GenerativeModel("gemini-2.5-flash")

    def review_code(self, request: ReviewRequest) -> ReviewResponse:
        signals_text = "\n".join(
            f"- {s.name} (severity {s.severity}): {s.description}"
            for s in request.signals
        )

        prompt = f"""
You are an expert code reviewer. Analyze the following code and detected signals.
Return ONLY a valid JSON object with no markdown, no explanation, no backticks.

Detected signals:
{signals_text}

Code:
{request.code}

Return this exact JSON structure:
{{
  "summary": "brief overall summary",
  "issues": [
    {{
      "type": "SECURITY | DESIGN | PERFORMANCE | READABILITY",
      "description": "what the issue is",
      "suggestion": "how to fix it"
    }}
  ]
}}
"""
        response = self.model.generate_content(prompt)
        raw = response.text.strip()

        parsed = json.loads(raw)
        issues = [CodeIssue(**issue) for issue in parsed["issues"]]
        return ReviewResponse(summary=parsed["summary"], issues=issues)