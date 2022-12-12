package org.camunda.bpm;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("extTask1")
public class ExternalWorker implements ExternalTaskHandler {

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    System.out.println("External Task `extTask1` running for instance " + externalTask.getProcessInstanceId());
    externalTaskService.complete(externalTask);
  }
}
