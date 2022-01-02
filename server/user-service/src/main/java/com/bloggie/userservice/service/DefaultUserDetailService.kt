package com.bloggie.userservice.service


import com.bloggie.userservice.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class DefaultUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService
{
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findById(username).get()
        return User(user.userName, user.password, user.blogAuthorities)
    }
}
