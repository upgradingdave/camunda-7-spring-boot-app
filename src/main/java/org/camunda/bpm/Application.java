package org.camunda.bpm;

import org.camunda.bpm.client.spring.annotation.EnableExternalTaskClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableExternalTaskClient(baseUrl = "http://localhost:8080/engine-rest")
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}

