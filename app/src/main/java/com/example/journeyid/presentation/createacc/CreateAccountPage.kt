package com.example.journeyid.presentation.createacc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.journeyid.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_create_account_page.*

class CreateAccountPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_page)

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
}