package com.example.account.rabbit

import com.example.account.entity.Client
import com.example.account.service.AccountService
import com.fasterxml.jackson.databind.ObjectMapper
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Receiver(
        private val accountMessageSender: Sender,
        private val accountService: AccountService
) {
    @RabbitListener(queues = ["client"])
    fun getMessage(client: Client) {
        if (client.deleted) {
            accountService.delete(accountService.find(client.guid!!))
        } else {
            try{
                client.account_uid = accountService.find(client.guid!!).account_uid.toLong()
            } catch (e: Exception){
                println(e.toString())
            }
            accountMessageSender.sendMessage(client)
        }
    }
}
