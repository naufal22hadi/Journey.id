package com.example.journeyid.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journeyid.R
import com.example.journeyid.presentation.createacc.CreateAccountPage
import com.example.journeyid.presentation.forgetpass.ForgetPassPage
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        createaccount.setOnClickListener {
            createAcc()
        }

        forgetpass.setOnClickListener {
            forgetPass()
        }
    }

    fun createAcc(){

        val intent = Intent(this, CreateAccountPage::class.java)
        startActivity(intent)
    }

    fun forgetPass(){

        val intent = Intent(this, ForgetPassPage::class.java)
        startActivity(intent)
    }
}