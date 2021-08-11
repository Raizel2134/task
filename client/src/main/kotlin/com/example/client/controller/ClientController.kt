package com.example.client.controller

import com.example.client.entity.Client
import com.example.client.rabbit.Sender
import com.example.client.service.ClientService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
class ClientController (
        private val clientService: ClientService
){
    @GetMapping
    fun showAll(): List<Client> {
        return clientService.findAll()
    }

    @GetMapping("/{id}")
    fun showOne(@PathVariable("id") id: Long): Client{
        return clientService.findById(id).orElseGet(throw RuntimeException())
    }

    @PostMapping("/create")
    fun createClient(@RequestBody client: Client) {
        return clientService.save(client)
    }

    @PostMapping("/update")
    fun updateClient(@RequestBody client: Client) {
        return clientService.save(client)
    }

    @PostMapping("/delete/{id}")
    fun deleteClient(@PathVariable("id") id: Long) {
        return clientService.delete(id)
    }
}