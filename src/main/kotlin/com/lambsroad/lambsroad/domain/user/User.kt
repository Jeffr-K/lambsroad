package com.lambsroad.lambsroad.domain.user

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    val createDateTime: OffsetDateTime = OffsetDateTime.now(),
    var updateDateTime: OffsetDateTime? = null
) {}