package com.example.eduexam

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.marginTop
import java.util.concurrent.TimeUnit


class QuestionActivity: ComponentActivity() {
    private lateinit var linearLayout: LinearLayout
    private lateinit var submitButton: Button
    private lateinit var countdownTimer: CountDownTimer
    private var timeLeftMillis: Long = 900000
    private lateinit var timerTextView: TextView
    private var totalQuestions: Int = 0
    private var attemptedQuestions: Int = 0
    private var correct: Int = 0
    private val questions = listOf(
        "1)What is Kotlin?",
        "2)What is a nullable type in Kotlin?",
        "3)What is a lambda expression in Kotlin?",
        "4)What is the primary constructor in Kotlin?",
        "5)What is the 'Elvis operator' in Kotlin?",
        "6)What is the 'lateinit' keyword used for in Kotlin?",
        "7)What is the purpose of the 'with' function in Kotlin?",
        "8)What is the difference between 'val' and 'var' in Kotlin?",
        "9)What is the 'sealed' class in Kotlin?",
        "10)What is the 'init' block in Kotlin?",
        "11)What is an extension function in Kotlin?",
        "12)What is the 'when' expression used for in Kotlin?",
        "13)What is the use of 'apply' function in Kotlin?",
        "14)What is the purpose of 'it' in Kotlin lambda expressions?",
        "15)What is the 'break' statement used for in Kotlin loops?"
    )

    private val options = listOf(
        listOf("A programming language", "A dessert", "A planet", "A car brand"),
        listOf("A type that cannot be null", "A type that can be null", "A type that is always initialized", "A type that is immutable"),
        listOf("A type of function", "A type of class", "A type of variable", "A type of expression"),
        listOf("A constructor that is executed first", "A constructor that takes parameters", "A constructor that is private", "A constructor that is optional"),
        listOf("A musical instrument", "A control structure", "A null-safe operator", "A mathematical operator"),
        listOf("For marking a variable as nullable", "For marking a variable as immutable", "For late initialization of variables", "For marking a variable as public"),
        listOf("For creating loops", "For initializing variables", "For applying extension functions", "For scoping operations"),
        listOf("val is for variables, var is for constants", "val is for mutable variables, var is for immutable variables", "val is for constants, var is for variables", "val and var are interchangeable"),
        listOf("A class that can be extended", "A class that cannot be instantiated", "A class with a single instance", "A class with private constructors"),
        listOf("A block of code executed before the main code", "A block of code executed after the main code", "A block of code executed during object creation", "A block of code executed during garbage collection"),
        listOf("A function defined outside of a class", "A function defined inside a class", "A function defined within another function", "A function with a single parameter"),
        listOf("For conditional branching", "For declaring variables", "For defining classes", "For creating loops"),
        listOf("For applying transformations to an object", "For defining extension functions", "For initializing variables", "For creating singletons"),
        listOf("It refers to the current class instance", "It refers to the current package", "It refers to the current function", "It refers to the current file"),
        listOf("To exit the loop immediately", "To skip the current iteration", "To jump to a specific label", "To terminate the program")
    )
    private var userResponses: MutableList<Int?> = MutableList(questions.size) { null }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        linearLayout = findViewById(R.id.linearLayout)
        submitButton = findViewById(R.id.submitButton)
        timerTextView = findViewById(R.id.timerTextView)

        findViewById<TextView>(R.id.testNameHeader).text = "1)Kotlin Basics Test"
        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        totalQuestions = questions.size

        for (i in questions.indices) {
            val questionText = questions[i]
            val optionsList = options[i]

            val questionTextView = TextView(this)
            questionTextView.text = questionText
            questionTextView.textSize = 18f
            questionTextView.setTypeface(null, Typeface.BOLD)

            val questionLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            questionLayoutParams.setMargins(0, dpToPx(16), 0, 0)
            questionTextView.layoutParams = questionLayoutParams

            val optionsRadioGroup = RadioGroup(this)
            optionsRadioGroup.layoutParams = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )

            for (j in optionsList.indices) {
                val optionRadioButton = RadioButton(this)
                optionRadioButton.text = optionsList[j]
                optionsRadioGroup.addView(optionRadioButton)

                optionRadioButton.setOnClickListener{
                    userResponses[i] = j
                }
            }

            linearLayout.addView(questionTextView)
            linearLayout.addView(optionsRadioGroup)
        }
        startTimer()
    }

    private fun startTimer() {
        countdownTimer = object : CountDownTimer(timeLeftMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                calculateResult()
                navigateToResultScreen()
            }
        }.start()
    }

    private fun updateTimerText() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftMillis)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftMillis) % 60
        timerTextView.text = String.format("%02d:%02d", minutes, seconds)
    }

    fun onSubmitButtonClick(view: View) {
        calculateResult()
        countdownTimer.cancel()
        navigateToResultScreen()
    }

    fun calculateResult() {
        attemptedQuestions = 0
        for (i in userResponses.indices) {
            if (userResponses[i] != null) {
                attemptedQuestions += 1

                if(userResponses[i] == getCorrectAnswerIndex(i)) {
                    correct += 1
                }
            }
        }
    }

    private fun navigateToResultScreen() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("totalQuestions", totalQuestions)
        intent.putExtra("attemptedQuestions", attemptedQuestions)
        intent.putExtra("correct",correct)
        startActivity(intent)
        finish()
    }

    private fun dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun getCorrectAnswerIndex(questionIndex: Int): Int {
        return 0
    }
}