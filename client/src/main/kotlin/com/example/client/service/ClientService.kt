package com.example.client.service

import com.example.client.entity.Client
import com.example.client.rabbit.Sender
import com.example.client.repository.ClientRepository
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientService(
        val clientRepository: ClientRepository,
        val sender: Sender
) {
    fun findById(id: Long): Optional<Client> {
        return clientRepository.findById(id)
    }

    fun findAll(): List<Client> {
        return clientRepository.findAll()
    }

    fun save(client: Client) {
        if (client.account_uid == 0L) sender.sendMessage(client)
        else clientRepository.save(client)
    }

    fun delete(id: Long) {
        val client: Optional<Client> = clientRepository.findById(id)
        return sender.sendMessage(client.get())
    }
}