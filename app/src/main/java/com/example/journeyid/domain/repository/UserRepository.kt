package com.example.journeyid.domain.repository

import com.example.journeyid.data.local.user.User
import com.example.journeyid.data.remote.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUserData(user: List<User>){
        userDao.insertAll(user)
    }

    suspend fun getUserData() : List<User>{
        return userDao.getUserLocalData()
    }

    suspend fun checkEmail(email : String) : String{
        return  userDao.checkedEmail(email)
    }
}