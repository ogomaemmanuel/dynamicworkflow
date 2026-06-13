package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;


@Component
public class HttpCallExecutor implements ActivityExecutor<HttpCallActivity> {

    private final RestClient restClient;

    public HttpCallExecutor(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @Override
    public Class<HttpCallActivity> supports() {
        return HttpCallActivity.class;
    }

    @Override
    public void execute(
            HttpCallActivity activity,
            WorkflowContext context
    ) {

        HttpHeaders headers = new HttpHeaders();

        Optional.ofNullable(activity.getHeaders())
                .ifPresent(headers::setAll);

        String url = resolveExpression(
                activity.getUrl(),
                context
        );

        Object body = activity.getBody();

        ResponseEntity<String> response =
                switch (activity.getMethod()) {
                    case GET -> restClient.get()
                            .uri(url)
                            .headers(h -> h.addAll(headers))
                            .retrieve()
                            .toEntity(String.class);

                    case POST -> restClient.post()
                            .uri(url)
                            .headers(h -> h.addAll(headers))
                            .body(body)
                            .retrieve()
                            .toEntity(String.class);

                    case PUT -> restClient.put()
                            .uri(url)
                            .headers(h -> h.addAll(headers))
                            .body(body)
                            .retrieve()
                            .toEntity(String.class);

                    case PATCH -> restClient.patch()
                            .uri(url)
                            .headers(h -> h.addAll(headers))
                            .body(body)
                            .retrieve()
                            .toEntity(String.class);

                    case DELETE -> restClient.delete()
                            .uri(url)
                            .headers(h -> h.addAll(headers))
                            .retrieve()
                            .toEntity(String.class);

                };

        context.put(
                activity.getId() + ".statusCode",
                response.getStatusCode().value()
        );

        context.put(
                activity.getId() + ".response",
                response.getBody()
        );
    }

    private String resolveExpression(
            String value,
            WorkflowContext context
    ) {

        if (value == null) {
            return null;
        }

        StandardEvaluationContext evalContext =
                new StandardEvaluationContext();

        evalContext.setVariables(context.variables());

        ExpressionParser parser =
                new SpelExpressionParser();

        return parser.parseExpression(value, new TemplateParserContext())
                .getValue(evalContext, String.class);
    }
}
