package com.example.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqListener {

  @RabbitListener(queues = "query-example-4-1")
  public void worker1(String message) {
    log.info("accepted on worker 1 : " + message);
  }

  @RabbitListener(queues = "query-example-4-2")
  public void worker2(String message) {
    log.info("accepted on worker 2 : " + message);
  }
}
