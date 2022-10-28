package com.example.physicsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

const val EXTRA_MESSAGE = "physicsquiz.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val trueButton: Button by lazy { findViewById(R.id.buttonTrue) }
    private val falseButton: Button by lazy { findViewById(R.id.buttonFalse) }
    private val questionText: TextView by lazy { findViewById(R.id.textViewQuestion) }

    private val questions: List<Question> = Questions.questions
    private var currentQuestionId: Int = 0
    private var currentQuestion: Question = questions[currentQuestionId]
    private var score: Int = 0
    private var cheatCount: Int = 0
    private var correctAnswerCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            cheatCount = savedInstanceState.getString("cheatCount")?.toInt()!!
            score = savedInstanceState.getString("score")?.toInt()!!
        }

        setQuestion()

        falseButton.setOnClickListener {
            submitAnswer(false)
        }
        trueButton.setOnClickListener {
            submitAnswer(true)
        }
    }

    private fun setQuestion() {
        currentQuestion = questions[currentQuestionId]
        questionText.text = currentQuestion.content
    }

    private fun submitAnswer(givenAnswer: Boolean) {
        if (isQuizFinished())
            showSummary()
        else {
            score += if (currentQuestion.answer == givenAnswer) 10 else 0
            correctAnswerCount += if (currentQuestion.answer == givenAnswer) 1 else 0
            currentQuestionId++
            setQuestion()
        }
    }

    private fun isQuizFinished(): Boolean {
        return currentQuestionId == questions.size - 1
    }

    private fun showSummary() {
        questionText.text = String.format(
            "You have answered correctly %s times to gain a score of %s\nmeanwhile using the cheat button %s times.",
            correctAnswerCount.toString(),
            score.toString(),
            cheatCount.toString()
        )
    }

    fun startCheatActivity(view: View) {
        val intent = Intent(this, CheatActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, currentQuestion.answer)
        }
        startActivity(intent)
    }
}