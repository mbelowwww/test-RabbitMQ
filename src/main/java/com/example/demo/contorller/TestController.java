package com.example.demo.contorller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/event")
@RestController
@Slf4j
@AllArgsConstructor
public class TestController {

  private final RabbitTemplate template;

  @GetMapping
  String home() {
    return "Empty mapping";
  }

  @GetMapping("/error")
  String error() {
    log.info("Emit as error");
    template.convertAndSend("error", "Error");
    return "Emit as error";
  }

  @GetMapping("/info")
  String info() {
    log.info("Emit as info");
    template.convertAndSend("info", "Info");
    return "Emit as info";
  }

  @GetMapping("/warning")
  String warning() {
    log.info("Emit as warning");
    template.convertAndSend("warning", "Warning");
    return "Emit as warning";
  }
}
