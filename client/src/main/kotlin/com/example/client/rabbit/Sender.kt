package com.example.client.rabbit

import com.example.client.entity.Client
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class Sender(
        var rabbitTemplate: RabbitTemplate
) {
    @Autowired
    fun accountMessageSender(rabbitTemplate: RabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate
    }

    fun sendMessage(client: Client) {
        rabbitTemplate.convertAndSend("client", client)
    }
}