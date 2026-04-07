from fastapi import FastAPI
from routers.review import router

app = FastAPI(
    title="AI Code Review - Python Microservice",
    description="LLM-powered code analysis via FastAPI",
    version="1.0.0"
)

app.include_router(router, prefix="/api/python")

@app.get("/health")
def health():
    return {"status": "Python microservice is running"}