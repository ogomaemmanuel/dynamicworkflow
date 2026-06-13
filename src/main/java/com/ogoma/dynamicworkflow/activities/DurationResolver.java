package com.ogoma.dynamicworkflow.activities;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DurationResolver {

    private final ExpressionParser parser =
            new SpelExpressionParser();

    public Duration resolve(
            TimerActivity timer,
            WorkflowContext context
    ) {

        if (timer.getDuration() != null) {
            return timer.getDuration();
        }

        StandardEvaluationContext evaluationContext =
                new StandardEvaluationContext();

        context.variables()
                .forEach(evaluationContext::setVariable);

        Object value = parser.parseExpression(
                        timer.getDurationExpression())
                .getValue(evaluationContext);

        if (value instanceof Integer days) {
            return Duration.ofDays(days);
        }

        if (value instanceof Long days) {
            return Duration.ofDays(days);
        }

        if (value instanceof Duration duration) {
            return duration;
        }

        throw new IllegalArgumentException(
                "Unsupported timer value: " + value
        );
    }
}
