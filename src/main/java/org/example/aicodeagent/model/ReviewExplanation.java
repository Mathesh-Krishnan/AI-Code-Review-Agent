package org.example.aicodeagent.model;

import java.util.List;

public record ReviewExplanation(
        List<String> detectedSignals,
        List<String> reasoningSteps
) {}