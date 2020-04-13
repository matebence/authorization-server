package com.blesk.authorizationserver.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {

    @Bean
    Queue verifyAccountQueue() {
        return new Queue("blesk.verifyAccountQueue", false);
    }

    @Bean
    DirectExchange verifyAccountExchange() {
        return new DirectExchange("blesk.verifyAccountExchange");
    }

    @Bean
    Binding verifyAccountQueueBinding() {
        return BindingBuilder.bind(verifyAccountQueue()).to(verifyAccountExchange()).with("blesk.verifyAccountRoutingKey");
    }

    @Bean
    Queue lastLoginQueue() {
        return new Queue("blesk.lastLoginQueue", false);
    }

    @Bean
    DirectExchange lastLoginExchange() {
        return new DirectExchange("blesk.lastLoginExchange");
    }

    @Bean
    Binding lastLoginQueueBinding() {
        return BindingBuilder.bind(lastLoginQueue()).to(lastLoginExchange()).with("blesk.lastLoginRoutingKey");
    }

    @Bean
    Queue createAccountQueue() {
        return new Queue("blesk.createAccountQueue", false);
    }

    @Bean
    DirectExchange createAccountExchange() {
        return new DirectExchange("blesk.createAccountExchange");
    }

    @Bean
    Binding createAccountQueueBinding() {
        return BindingBuilder.bind(createAccountQueue()).to(createAccountExchange()).with("blesk.createAccountRoutingKey");
    }

    @Bean
    Queue verifyActivationTokenQueue() {
        return new Queue("blesk.verifyActivationTokenQueue", false);
    }

    @Bean
    DirectExchange verifyActivationTokenExchange() {
        return new DirectExchange("blesk.verifyActivationTokenExchange");
    }

    @Bean
    Binding verifyActivationTokenBinding() {
        return BindingBuilder.bind(verifyActivationTokenQueue()).to(verifyActivationTokenExchange()).with("blesk.verifyActivationTokenRoutingKey");
    }

    @Bean
    Queue forgetPasswordQueue() {
        return new Queue("blesk.forgetPasswordQueue", false);
    }

    @Bean
    DirectExchange forgetPasswordExchange() {
        return new DirectExchange("blesk.forgetPasswordExchange");
    }

    @Bean
    Binding forgetPasswordQueueBinding() {
        return BindingBuilder.bind(forgetPasswordQueue()).to(forgetPasswordExchange()).with("blesk.forgetPasswordRoutingKey");
    }

    @Bean
    Queue verifyPasswordTokenQueue() {
        return new Queue("blesk.verifyPasswordTokenQueue", false);
    }

    @Bean
    DirectExchange verifyPasswordTokenExchange() {
        return new DirectExchange("blesk.verifyPasswordTokenExchange");
    }

    @Bean
    Binding verifyPasswordTokenBinding() {
        return BindingBuilder.bind(verifyPasswordTokenQueue()).to(verifyPasswordTokenExchange()).with("blesk.verifyPasswordTokenRoutingKey");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}