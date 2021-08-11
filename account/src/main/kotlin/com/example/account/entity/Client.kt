package com.example.account.entity

class Client(
        var id: Long,
        var guid: Int,
        var name: String,
        var date_birth: String,
        var account_uid: Long?,
        var deleted: Boolean
) {

}