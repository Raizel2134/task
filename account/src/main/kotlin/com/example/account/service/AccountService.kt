package com.example.account.service

import com.example.account.entity.Account
import com.example.account.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository
) {
    fun find(guid: Int): Account {
        return accountRepository.findByGuid(guid)
    }

    fun delete(account: Account) {
        return accountRepository.delete(account)
    }
}