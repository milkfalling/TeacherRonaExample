package com.example.androidwebserver.model

import java.sql.Timestamp

data class Member(
    var id: Int? = null,
    var username: String = "",
    var password: String = "",
    var nickname: String = "",
    var pass: Boolean? = null,
    var lastUpdateDate: Timestamp? = null,
    var avatarBase64: String? = null,
    var roleId: Int? = null
)