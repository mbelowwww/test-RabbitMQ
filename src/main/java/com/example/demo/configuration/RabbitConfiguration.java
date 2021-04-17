package com.example.demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitConfiguration {
  @Bean
  public ConnectionFactory connectionFactory() {
    return new CachingConnectionFactory("localhost");
  }

  @Bean
  public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setExchange("exchange-example-4");
    return rabbitTemplate;
  }

  @Bean
  public Queue myQueue1() {
    return new Queue("query-example-4-1");
  }

  @Bean
  public Queue myQueue2() {
    return new Queue("query-example-4-2");
  }

  @Bean
  public DirectExchange directExchange(){
    return new DirectExchange("exchange-example-4");
  }

  @Bean
  public Binding errorBinding1(){
    return BindingBuilder
        .bind(myQueue1())
        .to(directExchange())
        .with("error");
  }

  @Bean
  public Binding errorBinding2(){
    return BindingBuilder.bind(myQueue2())
        .to(directExchange())
        .with("error");
  }

  @Bean
  public Binding infoBinding1(){
    return BindingBuilder
        .bind(myQueue1())
        .to(directExchange())
        .with("info");
  }

  @Bean
  public Binding infoBinding2(){
    return BindingBuilder
        .bind(myQueue2())
        .to(directExchange())
        .with("info");
  }

  @Bean
  public Binding warningBinding1(){
    return BindingBuilder
        .bind(myQueue1())
        .to(directExchange())
        .with("warning");
  }

  @Bean
  public Binding warningBinding2(){
    return BindingBuilder
        .bind(myQueue2())
        .to(directExchange())
        .with("warning");
  }
}
