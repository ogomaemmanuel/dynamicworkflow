package com.ogoma.dynamicworkflow.activities;

import com.ogoma.dynamicworkflow.abstractions.ActivityExecutor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.springframework.http.HttpMethod.*;

@Component
public class HttpCallExecutor
        implements ActivityExecutor<HttpCallActivity> {

    private final RestClient restClient;

    public HttpCallExecutor(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @Override
    public String type() {
        return "HTTP_CALL";
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
                    case GET ->
                            restClient.get()
                                    .uri(url)
                                    .headers(h -> h.addAll(headers))
                                    .retrieve()
                                    .toEntity(String.class);

                    case POST ->
                            restClient.post()
                                    .uri(url)
                                    .headers(h -> h.addAll(headers))
                                    .body(body)
                                    .retrieve()
                                    .toEntity(String.class);

                    case PUT ->
                            restClient.put()
                                    .uri(url)
                                    .headers(h -> h.addAll(headers))
                                    .body(body)
                                    .retrieve()
                                    .toEntity(String.class);

                    case PATCH ->
                            restClient.patch()
                                    .uri(url)
                                    .headers(h -> h.addAll(headers))
                                    .body(body)
                                    .retrieve()
                                    .toEntity(String.class);

                    case DELETE ->
                            restClient.delete()
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

        context.variables()
                .forEach(evalContext::setVariable);

        ExpressionParser parser =
                new SpelExpressionParser();

        return parser.parseExpression(value)
                .getValue(evalContext, String.class);
    }
}
