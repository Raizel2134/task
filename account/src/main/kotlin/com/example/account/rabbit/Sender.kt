package com.example.account.rabbit

import com.example.account.entity.Client
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class Sender(private var rabbitTemplate: RabbitTemplate){
    @Autowired
    fun accountMessageSender(rabbitTemplate: RabbitTemplate) {
        if (rabbitTemplate != null){
            this.rabbitTemplate = rabbitTemplate
        }
    }

    fun sendMessage(client: Client){
        rabbitTemplate.convertAndSend("account", client)
    }
}