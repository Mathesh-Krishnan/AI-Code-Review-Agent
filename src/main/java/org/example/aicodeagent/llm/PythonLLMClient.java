package org.example.aicodeagent.llm;

import org.example.aicodeagent.analyzer.CodeSignal;
import org.example.aicodeagent.model.ReviewResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public class PythonLLMClient implements LLMClient {

    private final WebClient webClient;

    public PythonLLMClient(String pythonServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(pythonServiceUrl)
                .build();
    }

    @Override
    public ReviewResponse reviewCode(String code, List<CodeSignal> signals) {
        Map<String, Object> requestBody = Map.of(
                "code", code,
                "signals", signals.stream().map(s -> Map.of(
                        "name", s.name(),
                        "severity", s.severity(),
                        "description", s.description()
                )).toList()
        );

        return webClient.post()
                .uri("/api/python/review")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(ReviewResponse.class)
                .block();
    }
}