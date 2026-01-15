package org.example.aicodeagent.analyzer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CodeAnalyzer {

    public List<CodeSignal> analyze(String code) {

        List<CodeSignal> signals = new ArrayList<>();

        if (code.contains("static") && code.contains("new")) {
            signals.add(new CodeSignal(
                    "TIGHT_COUPLING",
                    7,
                    "Static object creation detected"
            ));
        }

        if (code.contains("for") || code.contains("while")) {
            signals.add(new CodeSignal(
                    "LOOP_USAGE",
                    5,
                    "Loop detected which may affect performance"
            ));
        }

        if (code.contains("password") || code.contains("secret")) {
            signals.add(new CodeSignal(
                    "HARDCODED_SECRET",
                    9,
                    "Hardcoded sensitive information detected"
            ));
        }

        if (code.length() > 500) {
            signals.add(new CodeSignal(
                    "LARGE_METHOD",
                    6,
                    "Large code block may impact readability"
            ));
        }

        return signals;
    }
}