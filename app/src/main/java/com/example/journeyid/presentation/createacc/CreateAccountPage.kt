package com.example.journeyid.presentation.createacc

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.journeyid.R
import com.example.journeyid.data.local.user.User
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_create_account_page.*
import org.koin.android.ext.android.inject
import java.util.regex.Pattern

class CreateAccountPage : AppCompatActivity() {

    private val viewModel by inject<CreateAccountViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_page)

        termCond()

        inputFullname()

        inputEmail()

        inputUsername()

        inputPassword()

        inputConfirmPassword()

        singupButton.setOnClickListener {
            insertDataToDatabase()
        }

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
                inputFullname.error = null
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
                inputPass.error = "Please input valid password"
            }
        }
    }

    private fun inputConfirmPassword(){
        inputConfirmPass.doOnTextChanged { text, start, before, count ->
            if(text!!.isNotEmpty()){
                layoutConfirmPass.helperText = " "
            } else {
                layoutConfirmPass.helperText = "Required*"
            }
        }
    }

    private fun insertDataToDatabase(){

        val fullName = inputFullname.text.toString()
        val email = inputEmail.text.toString()
        val userName = inputUser.text.toString()
        val password = inputPass.text.toString()


        if (inputCheck(fullName,email,userName,password) && inputFullname.error == null && inputEmail.error == null && inputUser.error == null && inputPass.error == null && checkTerm.isChecked){

            val user = User(fullName,email,userName,password,0)
            viewModel.insertUserData(listOf(user))
            Toast.makeText(this, "Sucsesfully added!", Toast.LENGTH_SHORT).show()
//            inputFullname.text = null
//            inputEmail.text = null
//            inputUser.text = null
//            inputPass.text = null
//            inputConfirmPass.text = null
            finish()
        } else if(inputEmail.text.toString() == viewModel.checkedEmail(email).toString()){
            Toast.makeText(this, "Email is already used!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please fill out of all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fullName : String, email : String, userName : String, password : String) : Boolean{
        return !(TextUtils.isEmpty(fullName)) &&
                !(TextUtils.isEmpty(email)) &&
                !(TextUtils.isEmpty(userName)) &&
                !(TextUtils.isEmpty(password))
    }
}