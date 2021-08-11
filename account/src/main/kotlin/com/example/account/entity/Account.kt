package com.example.account.entity

import javax.persistence.*

@Entity
@Table(name = "account")
class Account(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        @Column(name = "guid")
        var guid: Int,
        @Column(name = "account_uid")
        var account_uid: Int
) {
}