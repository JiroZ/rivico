package com.bloggie.userservice.controller

import com.bloggie.userservice.dto.Messages.*
import com.bloggie.userservice.dto.user.BlogUser
import com.bloggie.userservice.exceptions.BlogException
import com.bloggie.userservice.exceptions.CommentException
import com.bloggie.userservice.exceptions.UserException
import com.bloggie.userservice.service.DefaultUserService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class UserController(
    private val userService: DefaultUserService
) {
    @PostMapping("/user/auth")
    @Throws(UserException::class, BlogException::class, CommentException::class)
    fun authenticateUser(
        @RequestBody userAuthRequest: UserAuthRequest,
        bindingResult: BindingResult
    ): ResponseEntity<UserAuthResponse> {
        bindErrorResult(bindingResult)
        val userUserAuthResponse: UserAuthResponse = userService.authUser(userAuthRequest)

        return ResponseEntity.ok(userUserAuthResponse)
    }

    @PostMapping("/user/registration")
    @Throws(UserException::class, BlogException::class, CommentException::class)
    private fun registerUser(
        @Valid @RequestBody userRegistrationRequest: UserRegistrationRequest,
        bindingResult: BindingResult
    ): ResponseEntity<RegistrationMessage> {


        val registrationMessage: RegistrationMessage =
            userService.registerUser(userRegistrationRequest)

        bindErrorResult(bindingResult)
        return ResponseEntity.ok(registrationMessage)
    }

//    @PostMapping("/delete/{username}")
//    private fun deleteUser(@PathVariable username:String) {
//        userService.deleteUser(username)
//    }

    @GetMapping("/user/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<BlogUser> {
        return ResponseEntity.ok(userService.getUserByUsername(username))
    }

    @get:GetMapping("/user")
    val allUsers: ResponseEntity<List<Any>> =
        ResponseEntity.ok(userService.allUser)


    private fun bindErrorResult(bindingResult: BindingResult) {
        val errorMessage = StringBuilder()
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors
            for (error in errors) {
                errorMessage.append(error.defaultMessage!! + " ")
            }
            throw UserException(errorMessage.toString())
        }
    }
}