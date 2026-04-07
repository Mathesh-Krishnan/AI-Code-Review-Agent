from fastapi import APIRouter
from models.schemas import ReviewRequest, ReviewResponse
from services.mock_llm import MockLLM
from services.gemini_llm import GeminiLLM
import os
from dotenv import load_dotenv

load_dotenv()

router = APIRouter()

def get_llm_service():
    mode = os.getenv("LLM_MODE", "mock")
    if mode == "gemini":
        return GeminiLLM()
    return MockLLM()

@router.post("/review", response_model=ReviewResponse)
def review_code(request: ReviewRequest):
    llm = get_llm_service()
    return llm.review_code(request)