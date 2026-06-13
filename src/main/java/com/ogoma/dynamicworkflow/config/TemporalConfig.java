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
    public WorkflowClient workflowClient(AppProperties appProperties) {
        var service = WorkflowServiceStubs
                .newServiceStubs(WorkflowServiceStubsOptions.newBuilder()
                        .setTarget(appProperties.getTemporalAddress()).build());
        var clientOptions = WorkflowClientOptions.newBuilder()
                .setNamespace(appProperties.getTemporalNamespace())
                .build();
        return WorkflowClient.newInstance(service, clientOptions);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }


    @Bean
    public Worker workflowWorker(
            WorkerFactory workerFactory,
            DynamicActivityImpl dynamicActivity,
            AppProperties appProperties
            ) {

        Worker worker =
                workerFactory.newWorker(
                        appProperties.getTaskQueue()
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
