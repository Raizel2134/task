package com.example.client.repository

import com.example.client.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long> {
}