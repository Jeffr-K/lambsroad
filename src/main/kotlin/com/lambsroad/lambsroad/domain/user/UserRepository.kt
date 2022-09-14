package com.lambsroad.lambsroad.domain.user

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository: CrudRepository<User, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User u SET u.username=:username, u.password=:password, u.email=:email, u.phone=:phone WHERE u.id =:id")
    fun updateUser(
        @Param("id") id: Long,
        @Param("username") username: String,
        @Param("password") password: String,
        @Param("email") email: String,
        @Param("phone") phone: String
    )
}
