from pydantic import BaseModel
from typing import List, Optional

class CodeSignal(BaseModel):
    name: str
    severity: int
    description: str

class ReviewRequest(BaseModel):
    code: str
    signals: List[CodeSignal]
    focusArea: Optional[str] = None

class CodeIssue(BaseModel):
    type: str
    description: str
    suggestion: str

class ReviewResponse(BaseModel):
    summary: str
    issues: List[CodeIssue]