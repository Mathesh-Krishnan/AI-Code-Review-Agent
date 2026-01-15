package org.example.aicodeagent.model;

public record CodeIssue(
        String type,
        String description,
        String suggestion
) {}
