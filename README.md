🤖 AI Code Review Agent


📌 Overview
The AI Code Review Agent is a polyglot microservice system built with Java 21, Spring Boot, Python, and FastAPI that analyzes source code and identifies security vulnerabilities, design flaws, and code quality issues. The system combines a rule-based Java analysis engine with a Python-powered AI microservice backed by Google Gemini, demonstrating real-world agentic AI architecture with clean software engineering principles.
The system accepts raw source code through RESTful APIs, processes it via an agent-driven analysis layer, delegates intelligent reasoning to a Python FastAPI microservice, and produces structured, explainable feedback with actionable recommendations.

✨ Key Features

Agent-based architecture following observe → reason → decide pattern
Rule-driven detection of security, design, performance and readability issues
Python FastAPI microservice for AI-driven reasoning
Google Gemini API integration for intelligent, human-like suggestions
Seamless switch between mock and real LLM with a single config change
Explainable AI outputs showing reasoning steps and detected signals
RESTful APIs for easy integration
Clean, layered architecture with separation of concerns
Extensible LLM abstraction — swap any AI provider with zero code change

🏗️ Architecture Overview

Client (Postman)
↓
Spring Boot (port 8080)
├── Controller Layer   → Exposes REST APIs
├── Agent Layer        → Orchestrates observe, reason, decide flow
├── Analyzer Layer     → Rule-based code signal detection
├── Model Layer        → Request/Response domain models
└── LLM Layer          → Abstracted interface (mock or Python microservice)
↓ HTTP REST
Python FastAPI (port 8000)
├── Router Layer       → Exposes /api/python/review endpoint
├── Service Layer      → LLMInterface abstraction
├── MockLLM            → Hardcoded suggestions for testing
└── GeminiLLM          → Real Google Gemini API integration
Java handles structured analysis and orchestration. Python handles AI-driven reasoning. Both services run independently and communicate over HTTP.

🛠️ Technology Stack
Java Side:

Language: Java 21
Framework: Spring Boot 3
API: Spring Web, Spring WebFlux (WebClient)
Build Tool: Maven
JSON Processing: Jackson

Python Side:

Language: Python 3.12
Framework: FastAPI
Server: Uvicorn
AI Integration: Google Gemini API (gemini-2.5-flash)
HTTP Client: httpx
Config: python-dotenv
Validation: Pydantic

Tools:
Postman, Git, GitHub, IntelliJ IDEA, PyCharm


🧠 How the Agent Works

* Client sends source code to the Java REST API
* Controller forwards request to the Code Review Agent
* Agent invokes CodeAnalyzer which scans code and returns CodeSignals with severity scores
* Agent sorts signals by severity — highest priority first
* Based on config, request is routed to mock or Python microservice
* Python microservice sends code and signals to Google Gemini
* Gemini returns intelligent, human-like suggestions
* Structured response flows back to the client


📁 Project Structure

ai-code-review-agent/
├── src/main/java/org/example/aicodeagent/
│   ├── agent/          → CodeReviewAgent, ReviewCategory
│   ├── analyzer/       → CodeAnalyzer, CodeSignal
│   ├── controller/     → CodeReviewController
│   ├── llm/            → LLMClient, OpenAIClient, PythonLLMClient, LLMClientConfig
│   └── model/          → ReviewRequest, ReviewResponse, CodeIssue, ReviewExplanation
├── src/main/resources/
│   └── application.yml → LLM mode and Python service URL config
└── ai-review-python/
├── models/         → Pydantic schemas
├── routers/        → FastAPI endpoints
├── services/       → LLMInterface, MockLLM, GeminiLLM
├── main.py         → FastAPI app entry point
└── .env            → API key and LLM mode config

🚀 Getting Started
**Prerequisites**

Java 21
Maven
Python 3.12
Gemini API key (free at aistudio.google.com)

**Run Python Microservice**
cd ai-review-python
pip install fastapi uvicorn httpx google-generativeai python-dotenv pydantic
python -m uvicorn main:app --reload --port 8000

**Run Spring Boot**
mvn clean install
# Run AiCodeAgentApplication.java from IntelliJ

⚙️ Configuration
application.yml — controls which LLM client Java uses:
llm:
mode: mock        # change to 'python' to route through FastAPI
python:
service:
url: http://localhost:8000

**ai-review-python/.env — controls which LLM Python uses:**
GEMINI_API_KEY=your_key_here
LLM_MODE=mock       # change to 'gemini' for real AI responses


📡 API Endpoints
Review Code
POST /api/review
Request: {
"code": "public class Test { String password = '123'; }",
"focusArea": "security"
}

**Response:**
{
"summary": "Agent analyzed the code, prioritized risks, and generated actionable recommendations.",
"issues": [
{
"type": "SECURITY",
"description": "Hardcoded sensitive information detected",
"suggestion": "Move secrets to environment variables or a vault"
}
]
}

Explain Analysis
POST /api/review/explain
Response:
{
"detectedSignals": [
"HARDCODED_SECRET (severity 9)",
"TIGHT_COUPLING (severity 7)"
],
"reasoningSteps": [
"Detected HARDCODED_SECRET because Hardcoded sensitive information detected",
"Detected TIGHT_COUPLING because Static object creation detected"
]
}

**Python Health Check**
GET http://localhost:8000/health
{"status": "Python microservice is running"}

🎯 Design Principles

Clean Architecture
Separation of Concerns
Polyglot Microservice Design
Pluggable LLM Abstraction
Explainable AI outputs
Security best practices (API keys in .env, gitignored)


🔮 Future Enhancements

Docker + Docker Compose for one command startup
Support for additional LLM providers (Groq, Ollama, OpenAI)
CI/CD integration for pull request reviews
Multi-language code analysis beyond Java
Frontend UI for code submission and review display


🏁 Conclusion

This project demonstrates a production-minded, polyglot microservice architecture combining Java Spring Boot and Python FastAPI with real Google Gemini AI integration. It highlights strong backend engineering, thoughtful system design, LLM integration patterns, and practical application of agentic reasoning — making it a strong showcase for backend, Java, Python, and AI-adjacent skills in a professional portfolio.