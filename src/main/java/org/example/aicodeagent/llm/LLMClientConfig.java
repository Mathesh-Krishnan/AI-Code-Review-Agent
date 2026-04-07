package org.example.aicodeagent.llm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMClientConfig {

    @Value("${llm.mode:mock}")
    private String llmMode;

    @Value("${python.service.url:http://localhost:8000}")
    private String pythonServiceUrl;

    @Bean
    public LLMClient llmClient() {
        if ("python".equalsIgnoreCase(llmMode)) {
            return new PythonLLMClient(pythonServiceUrl);
        }
        return new OpenAIClient();
    }
}