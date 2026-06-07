package com.ogoma.dynamicworkflow.activities;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class ConditionEvaluator {

    private final ExpressionParser parser =
            new SpelExpressionParser();

    public boolean evaluate(
            String expression,
            WorkflowContext context
    ) {

        if (expression == null || expression.isBlank()) {
            return true;
        }

        StandardEvaluationContext evalContext =
                new StandardEvaluationContext();

        context.variables()
                .forEach(evalContext::setVariable);

        return Boolean.TRUE.equals(parser
                .parseExpression(expression)
                .getValue(evalContext, Boolean.class));
    }
}