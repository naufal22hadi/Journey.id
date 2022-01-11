package com.example.journeyid.presentation.createacc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeyid.data.local.user.User
import com.example.journeyid.domain.usecases.UserUseCase
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _userData = MutableLiveData<List<User>>()
    val userData : LiveData<List<User>> = _userData

    fun insertUserData(user: List<User>){
        viewModelScope.launch{
            userUseCase.insertUserData(user)
        }
    }

    suspend fun getUserData() : List<User>{
        return userUseCase.getUserData()
    }

    suspend fun checkEmail(email : String) : String{
        return userUseCase.checkEmail(email)
    }

    fun checkedEmail(email : String){
        viewModelScope.launch {
            checkEmail(email)
        }
    }
}