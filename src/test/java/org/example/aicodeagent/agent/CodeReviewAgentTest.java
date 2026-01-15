package org.example.aicodeagent.agent;

import org.example.aicodeagent.analyzer.CodeAnalyzer;
import org.example.aicodeagent.llm.OpenAIClient;
import org.example.aicodeagent.model.ReviewExplanation;
import org.example.aicodeagent.model.ReviewRequest;
import org.example.aicodeagent.model.ReviewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeReviewAgentTest {

    private CodeReviewAgent agent;

    @BeforeEach
    void setup() {
        CodeAnalyzer analyzer = new CodeAnalyzer();
        OpenAIClient llmClient = new OpenAIClient();
        agent = new CodeReviewAgent(analyzer, llmClient);
    }

    @Test
    void shouldDetectSecurityIssue() {
        ReviewRequest request = new ReviewRequest(
                "public class Test { String password = \"123\"; }",
                "all"
        );

        ReviewResponse response = agent.review(request);

        assertThat(response.issues())
                .isNotEmpty();

        assertThat(response.issues().getFirst().type())
                .isEqualTo("SECURITY");
    }

    @Test
    void shouldReturnEmptyIssuesForCleanCode() {
        ReviewRequest request = new ReviewRequest(
                "public class Clean { void run() { System.out.println(\"Hello\"); } }",
                "all"
        );

        ReviewResponse response = agent.review(request);

        assertThat(response.issues()).isEmpty();
    }

    @Test
    void shouldExplainReasoning() {
        ReviewExplanation explanation =
                agent.explain("public class Test { String password = \"123\"; }");

        assertThat(explanation.detectedSignals())
                .isNotEmpty();

        assertThat(explanation.reasoningSteps())
                .anyMatch(step -> step.contains("HARDCODED_SECRET"));
    }
}
