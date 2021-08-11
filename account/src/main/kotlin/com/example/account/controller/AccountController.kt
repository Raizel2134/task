package com.example.account.controller

import com.example.account.entity.Account
import com.example.account.service.AccountService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account", produces = [MediaType.APPLICATION_JSON_VALUE])
class AccountController (
        private val accountService: AccountService
){
    @GetMapping("/{guid}")
    fun showAccount(@PathVariable("guid") guid: Int) : Account {
        return accountService.find(guid)
    }
}