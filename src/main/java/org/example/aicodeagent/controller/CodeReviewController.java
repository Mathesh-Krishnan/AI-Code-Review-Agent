package org.example.aicodeagent.controller;

import org.example.aicodeagent.agent.CodeReviewAgent;
import org.example.aicodeagent.model.ReviewExplanation;
import org.example.aicodeagent.model.ReviewRequest;
import org.example.aicodeagent.model.ReviewResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class CodeReviewController {

    private final CodeReviewAgent agent;

    public CodeReviewController(CodeReviewAgent agent) {
        this.agent = agent;
    }

    @PostMapping
    public ReviewResponse review(@RequestBody ReviewRequest request) {
        return agent.review(request);
    }

    // ✅ FIXED: POST instead of GET
    @PostMapping("/explain")
    public ReviewExplanation explain(@RequestBody ReviewRequest request) {
        return agent.explain(request.code());
    }
}
