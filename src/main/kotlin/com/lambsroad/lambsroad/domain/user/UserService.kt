package com.lambsroad.lambsroad.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun create(user: User): User {
        return userRepository.save(user)
    }

    fun update(user: User) {
        return userRepository.updateUser(user.id, user.username, user.password, user.email, user.phone)
    }

    fun delete(user: User) {
        return userRepository.delete(user);
    }

    fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow()
    }

}
