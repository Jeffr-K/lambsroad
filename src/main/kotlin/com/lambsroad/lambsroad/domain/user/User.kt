package com.lambsroad.lambsroad.domain.user

import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
data class User(
                @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id: Long,
                val username: String,
                var password: String,
                val email: String,
                val phone: String,
                @CreationTimestamp
                @Column(nullable = false, updatable = false)
                val createDateTime: OffsetDateTime = OffsetDateTime.now(),
                @CreationTimestamp
                @Column(nullable = false, updatable = false)
                var updateDateTime: OffsetDateTime? = null
) {}

