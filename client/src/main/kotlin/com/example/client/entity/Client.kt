package com.example.client.entity

import javax.persistence.*

@Entity
@Table(name = "clients")
class Client(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        var guid: Int,
        var name: String,
        var date_birth: String,
        var account_uid: Long?,
        @Column(name = "is_deleted")
        var deleted: Boolean
) {
}