package com.ogoma.dynamicworkflow.config;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    @Bean
    public WorkflowClient workflowClient() {
        var service = WorkflowServiceStubs
                .newServiceStubs(WorkflowServiceStubsOptions.newBuilder()
                        .setTarget("localhost:7233").build());
        var clientOptions = WorkflowClientOptions.newBuilder()
                .setNamespace("default")
                .build();
        return WorkflowClient.newInstance(service, clientOptions);
    }

    @Bean
    public WorkerFactory workerFactory() {
        return WorkerFactory.newInstance(workflowClient());
    }
}
