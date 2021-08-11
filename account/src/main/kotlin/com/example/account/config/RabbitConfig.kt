package com.example.account.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory

@Configuration
class RabbitConfig {
    val QUEUE_ACCOUNT = "account"
    val QUEUE_CLIENT = "client"

    @Bean
    fun accountQueue() : Queue{
        return QueueBuilder.durable(QUEUE_ACCOUNT).build()
    }

    @Bean
    fun clientQueue() : Queue{
        return QueueBuilder.durable(QUEUE_CLIENT).build()
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory) : RabbitTemplate{
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = producerJackson2MessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter(ObjectMapper().registerKotlinModule())
    }

    @Bean
    fun messageHandlerMethodFactory(): MessageHandlerMethodFactory {
        val messageHandlerMethodFactory = DefaultMessageHandlerMethodFactory()
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter())
        return messageHandlerMethodFactory
    }

    fun configureRabbitListeners(registrar: RabbitListenerEndpointRegistrar) {
        registrar.messageHandlerMethodFactory = messageHandlerMethodFactory()
    }

    @Bean
    fun consumerJackson2MessageConverter(): MappingJackson2MessageConverter {
        return MappingJackson2MessageConverter()
    }
}
