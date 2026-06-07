package com.ogoma.dynamicworkflow.config;

import com.ogoma.dynamicworkflow.activities.DynamicActivityImpl;
import com.ogoma.dynamicworkflow.workflows.DynamicWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.ApplicationRunner;
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


    @Bean
    public Worker workflowWorker(
            WorkerFactory workerFactory,
            DynamicActivityImpl dynamicActivity
    ) {

        Worker worker =
                workerFactory.newWorker(
                        "DYNAMIC_WORKFLOW_QUEUE"
                );

        worker.registerWorkflowImplementationTypes(
                DynamicWorkflowImpl.class
        );

        worker.registerActivitiesImplementations(
                dynamicActivity
        );

        return worker;
    }

    @Bean
    public ApplicationRunner workerStarter(
            WorkerFactory workerFactory
    ) {

        return args -> workerFactory.start();
    }
}
