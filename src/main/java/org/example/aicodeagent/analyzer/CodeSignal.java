package org.example.aicodeagent.analyzer;

public record CodeSignal(
        String name,
        int severity,
        String description
) {}