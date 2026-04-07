from abc import ABC, abstractmethod
from models.schemas import ReviewRequest, ReviewResponse

class LLMInterface(ABC):
    @abstractmethod
    def review_code(self, request: ReviewRequest) -> ReviewResponse:
        pass