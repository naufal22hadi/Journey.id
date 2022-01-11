package com.example.journeyid.domain.usecases

import com.example.journeyid.data.local.user.User
import com.example.journeyid.domain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun insertUserData(user: List<User>){
        userRepository.insertUserData(user)
    }

    suspend fun getUserData() : List<User>{
        return userRepository.getUserData()
    }

    suspend fun checkEmail(email : String) : String{
        return userRepository.checkEmail(email)
    }
}