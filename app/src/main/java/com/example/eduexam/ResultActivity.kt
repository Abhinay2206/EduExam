package com.example.eduexam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ResultActivity: ComponentActivity() {
    private var totalQuestions: Int = 0
    private var attemptedQuestions: Int = 0
    private var correct: Int = 0

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        attemptedQuestions = intent.getIntExtra("attemptedQuestions", 0)
        correct = intent.getIntExtra("correct",0)
        val unattemptedQuestions = totalQuestions - attemptedQuestions

        findViewById<TextView>(R.id.headerTextView).text = "Result"

        findViewById<TextView>(R.id.totalQuestionsTextView).text = "Total Questions: $totalQuestions"
        findViewById<TextView>(R.id.attemptedTextView).text = "Attempted: $attemptedQuestions"
        findViewById<TextView>(R.id.unattemptedTextView).text = "Unattempted: $unattemptedQuestions"
        findViewById<TextView>(R.id.correctTextView).text = "Correct: $correct"

        findViewById<Button>(R.id.attemptAgainButton).setOnClickListener {
            navigateToScreen3()
        }

        findViewById<Button>(R.id.homeButton).setOnClickListener {
            navigateToScreen2()
        }
    }

    private fun navigateToScreen2() {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToScreen3() {
        val intent = Intent(this, QuestionActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onAttemptAgainClick(view: View) {
        navigateToScreen3()
    }

    fun onHomeClick(view: View) {
        navigateToScreen2()
    }
}