package com.example.physicsquiz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
    private val answerText: TextView by lazy { findViewById(R.id.textViewAnswer) }

    private var answer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answer = intent.getBooleanExtra(EXTRA_MESSAGE, false)
        setAnswer()
    }

    private fun setAnswer() {
        answerText.text =
            String.format(
                "The correct answer is %s", answer.toString().replaceFirstChar { it.uppercase() })
    }

    fun browseForAnswer(view: View) {
        val url = "https://www.google.com/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}