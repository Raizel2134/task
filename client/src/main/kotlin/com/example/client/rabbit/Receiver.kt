package com.example.client.rabbit

import com.example.client.entity.Client
import com.example.client.service.ClientService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Receiver(
        val clientService: ClientService
) {
    @RabbitListener(queues = ["account"])
    fun getMessage(client: Client) {
        clientService.save(client)
    }
}