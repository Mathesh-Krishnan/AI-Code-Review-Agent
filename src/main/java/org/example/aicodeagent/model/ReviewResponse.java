package org.example.aicodeagent.model;

import java.util.List;

public record ReviewResponse(
        String summary,
        List<CodeIssue> issues
) {}