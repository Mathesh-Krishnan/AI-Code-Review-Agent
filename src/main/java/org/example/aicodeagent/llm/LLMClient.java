package org.example.aicodeagent.llm;

import org.example.aicodeagent.analyzer.CodeSignal;
import org.example.aicodeagent.model.ReviewResponse;

import java.util.List;

public interface LLMClient {

    ReviewResponse reviewCode(
            String code,
            List<CodeSignal> signals
    );
}