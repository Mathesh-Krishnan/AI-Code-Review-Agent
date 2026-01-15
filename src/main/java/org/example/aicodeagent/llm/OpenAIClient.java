package org.example.aicodeagent.llm;

import org.example.aicodeagent.analyzer.CodeSignal;
import org.example.aicodeagent.model.CodeIssue;
import org.example.aicodeagent.model.ReviewResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenAIClient implements LLMClient {

    @Override
    public ReviewResponse reviewCode(
            String code,
            List<CodeSignal> signals
    ) {

        List<CodeIssue> issues = new ArrayList<>();

        for (CodeSignal signal : signals) {
            switch (signal.name()) {
                case "HARDCODED_SECRET" -> issues.add(
                        new CodeIssue(
                                "SECURITY",
                                signal.description(),
                                "Move secrets to environment variables or a vault"
                        )
                );
                case "TIGHT_COUPLING" -> issues.add(
                        new CodeIssue(
                                "DESIGN",
                                signal.description(),
                                "Introduce dependency injection to reduce coupling"
                        )
                );
                case "LOOP_USAGE" -> issues.add(
                        new CodeIssue(
                                "PERFORMANCE",
                                signal.description(),
                                "Review loop complexity and optimize if needed"
                        )
                );
                case "LARGE_METHOD" -> issues.add(
                        new CodeIssue(
                                "READABILITY",
                                signal.description(),
                                "Break large methods into smaller units"
                        )
                );
            }
        }

        String summary = issues.isEmpty()
                ? "Code looks clean with no major issues detected."
                : "Agent analyzed the code, prioritized risks, and generated actionable recommendations.";

        return new ReviewResponse(summary, issues);
    }
}