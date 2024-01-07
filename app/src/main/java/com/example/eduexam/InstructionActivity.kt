package com.example.eduexam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.ComponentActivity

class InstructionActivity : ComponentActivity() {
    private lateinit var confirmCheckBox: CheckBox
    private lateinit var confirmationMessage: TextView
    private lateinit var nextButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        confirmCheckBox = findViewById(R.id.confirmCheckBox)
        confirmationMessage = findViewById(R.id.confirmationMessage)
        nextButton = findViewById(R.id.nextButton)
    }

    fun onBackButtonClick(view: View) {
        finish()
    }

    @SuppressLint("SetTextI18n")
    fun onNextButtonClick(view: View) {
        if (confirmCheckBox.isChecked) {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        } else {
            confirmationMessage.text = "Please confirm before proceeding."
        }
    }
}