package org.example.aicodeagent.agent;

import org.example.aicodeagent.analyzer.CodeAnalyzer;
import org.example.aicodeagent.analyzer.CodeSignal;
import org.example.aicodeagent.llm.LLMClient;
import org.example.aicodeagent.model.ReviewRequest;
import org.example.aicodeagent.model.ReviewResponse;
import org.springframework.stereotype.Component;

import org.example.aicodeagent.model.ReviewExplanation;


import java.util.Comparator;
import java.util.List;

@Component
public class CodeReviewAgent {

    private final CodeAnalyzer analyzer;
    private final LLMClient llmClient;

    public CodeReviewAgent(CodeAnalyzer analyzer, LLMClient llmClient) {
        this.analyzer = analyzer;
        this.llmClient = llmClient;
    }

    public ReviewResponse review(ReviewRequest request) {

        // 1️⃣ Observe
        List<CodeSignal> signals = analyzer.analyze(request.code());

        // 2️⃣ Reason: sort by severity
        List<CodeSignal> prioritizedSignals = signals.stream()
                .sorted(Comparator.comparingInt(CodeSignal::severity).reversed())
                .toList();

        // 3️⃣ Decide + Delegate
        return llmClient.reviewCode(
                request.code(),
                prioritizedSignals
        );
    }

    public ReviewExplanation explain(String code) {

        var signals = analyzer.analyze(code);

        var detectedSignals = signals.stream()
                .map(signal -> signal.name() + " (severity " + signal.severity() + ")")
                .toList();

        var reasoningSteps = signals.stream()
                .sorted((a, b) -> Integer.compare(b.severity(), a.severity()))
                .map(signal ->
                        "Detected " + signal.name()
                                + " because " + signal.description()
                )
                .toList();

        return new ReviewExplanation(detectedSignals, reasoningSteps);
    }

}