# AI Code Review Agent

## Overview

The **AI Code Review Agent** is a Java 21 and Spring Boot–based backend application designed to analyze source code and identify potential security and code quality issues. Instead of relying on traditional static analysis tools or paid AI APIs, this project demonstrates how *agentic AI concepts* can be applied using a rule-based reasoning engine combined with clean software architecture principles.

The system accepts raw source code as input through RESTful APIs, processes it via an agent-driven analysis layer, and produces structured, explainable feedback with actionable recommendations. The project focuses on clarity, extensibility, and real-world backend design rather than experimental machine learning.

---

## Key Features

* Agent-based architecture for code analysis
* Rule-driven detection of security and quality issues
* Explainable reasoning for each detected issue
* Actionable improvement suggestions
* RESTful APIs for easy integration
* Clean, layered architecture
* Unit testing with JUnit 5 and AssertJ
* No paid external AI or LLM dependencies

---

## Architecture Overview

The project follows a clean, layered design to ensure maintainability and extensibility:

* **Controller Layer**: Exposes REST APIs for code review and explanation
* **Agent Layer**: Orchestrates analysis and decision-making logic
* **Analyzer Layer**: Applies rule-based logic to detect issues in code
* **Model Layer**: Defines request/response and domain models
* **LLM Layer (Pluggable)**: Abstracted interface for future AI/LLM integration

This separation allows individual components to evolve independently and makes the system easy to extend with additional analysis rules or AI models.

---

## Technology Stack

* **Language**: Java 21
* **Framework**: Spring Boot 3
* **API**: Spring Web (RESTful APIs)
* **Build Tool**: Maven
* **Testing**: JUnit 5, AssertJ
* **JSON Processing**: Jackson
* **Tools**: Postman, Git, IntelliJ IDEA

---

## How the Agent Works

1. The client sends source code to the API.
2. The controller forwards the request to the Code Review Agent.
3. The agent invokes the analyzer to inspect the code using predefined rules.
4. Detected issues are categorized (e.g., security risks).
5. The agent generates explainable reasoning and suggestions.
6. A structured response is returned to the client.

This workflow simulates how intelligent agents reason about problems and produce transparent outputs.

---

## API Endpoints

### Review Code

**POST** `/api/review`

**Request Body:**

```json
{
  "code": "public class Test { String password = \"123\"; }",
  "mode": "all"
}
```

**Response:**

```json
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
```

---

### Explain Analysis

**GET** `/api/review/explain?code=<source_code>`

Returns reasoning signals and steps explaining why specific issues were detected.

---

## Testing

The project includes unit tests written with JUnit 5 and AssertJ to validate:

* Issue detection logic
* Explanation generation
* Agent behavior under different code inputs

Tests are located under `src/test/java` and can be run using:

```bash
mvn clean test
```

---

## Design Principles Followed

* Clean Architecture
* Separation of Concerns
* Testability
* Extensibility
* Explicit and explainable reasoning

---

## Future Enhancements (Optional)

While the current version is intentionally lightweight, the architecture supports:

* Additional issue categories (performance, maintainability)
* Automatic refactoring suggestions
* Multi-agent collaboration
* Real LLM integration (OpenAI, Ollama, etc.)
* CI/CD integration for pull request reviews

---

## Conclusion

This project demonstrates how intelligent, agent-based systems can be built using modern Java and Spring Boot without heavy AI dependencies. It highlights strong backend engineering practices, thoughtful system design, and practical application of agentic reasoning—making it suitable for showcasing backend, Java, and AI-adjacent skills in a professional portfolio.
