package com.lambsroad.lambsroad.domain.user.dto

import com.lambsroad.lambsroad.domain.user.User

data class UserCreateDto(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
) {}
