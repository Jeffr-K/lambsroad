package com.lambsroad.lambsroad.domain.user.dto

data class UserDeleteDto(
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
) {}
