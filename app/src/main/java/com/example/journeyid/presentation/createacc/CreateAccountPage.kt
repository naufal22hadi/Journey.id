package com.example.journeyid.presentation.createacc

import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.journeyid.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_create_account_page.*
import java.util.regex.Pattern

class CreateAccountPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_page)

        termCond()

        inputFullname()

        inputEmail()

        inputUsername()

        inputPassword()

        inputConfirmPassword()

    }

    private fun termCond(){
        textTerm.setOnClickListener {
            val termCond =BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            val viewTermCond = layoutInflater.inflate(R.layout.term_condition, findViewById(R.id.bottomSheet) as LinearLayout?)

            val close =viewTermCond.findViewById<TextView>(R.id.closeSign)
            close.setOnClickListener {
                termCond.dismiss()
            }
            termCond.setContentView(viewTermCond)
            termCond.show()
        }
    }

    private fun inputFullname(){
        inputFullname.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()){
                layoutFullname.helperText = " "
            }else if(text!!.isEmpty()){
                inputFullname.error = "Your name is required!!"
            }
        }
    }

    private fun CharSequence?.isValidEmail(): Boolean {
        return EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun inputEmail(){
        inputEmail.doOnTextChanged { text, start, before, count ->
            if(text.isValidEmail()){
                inputEmail.error = null
                layoutEmail.helperText = " "
            } else {
                inputEmail.error = "Please input valid email!!"
            }
        }
    }

    private fun inputUsername(){
        inputUser.doOnTextChanged { text, start, before, count ->
            if(text!!.length >= 4 && text!!.length <= 12){
                inputUser.error = null
                layoutUser.helperText = " "
            } else {
                inputUser.error = "Username minimal 4 and maximal 12 character!!"
            }
        }
    }

    private fun CharSequence?.isValidPassword() : Boolean {
            val passwordPattern = Pattern.compile("^"+"(?=.*[0-9])"+"(?=.*[a-z])" +"(?=.*[A-Z])" +"(?=.*[a-zA-Z])" +"(?=.*[@#$%^&+=])"+"(?=\\S+$)" +".{8,}" +"$")
            return passwordPattern.matcher(this).matches()
    }

    private fun inputPassword(){
        inputPass.doOnTextChanged { text, start, before, count ->
            if(text.isValidPassword()){
                inputPass.error = null
                layoutPass.helperText = " "
            } else {
                layoutPass.helperText = "Password min 8 char, atleast 1 uppercase, lowercase, number and symbol"
            }
        }
    }

    private fun inputConfirmPassword(){
        inputConfirmPass.doOnTextChanged { text, start, before, count ->
            if(text!!.isNotEmpty()){
                layoutConfirmPass.helperText = " "
            } else {
                layoutConfirmPass.helperText = "Rquired*"
            }
        }
    }
}