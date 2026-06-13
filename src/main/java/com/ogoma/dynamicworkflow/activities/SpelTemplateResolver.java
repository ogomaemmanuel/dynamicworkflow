package com.ogoma.dynamicworkflow.activities;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class SpelTemplateResolver {

    private final ExpressionParser parser =
            new SpelExpressionParser();

    private final ParserContext parserContext =
            new TemplateParserContext();

    public String resolve(
            String template,
            WorkflowContext context
    ) {

        if (template == null) {
            return null;
        }

        EvaluationContext evalContext =
                SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.variables()
                .forEach(evalContext::setVariable);
        return parser
                .parseExpression(
                        template,
                        parserContext
                )
                .getValue(
                        evalContext,
                        String.class
                );
    }

    public <T> T resolve(
            String template,
            WorkflowContext context,
            Class<T> type
    ) {

        EvaluationContext evalContext =
                SimpleEvaluationContext.forReadWriteDataBinding().build();;

        context.variables()
                .forEach(evalContext::setVariable);

        return parser
                .parseExpression(
                        template,
                        parserContext
                )
                .getValue(
                        evalContext,
                        type
                );
    }
}
