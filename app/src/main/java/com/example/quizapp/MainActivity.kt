package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var next_Button : Button

    private val question = listOf<Question>(
        Question("¿La luna es de queso?", false),
        Question("¿El cielo realmente es azul?", false),
        Question("¿La tierra es plana?", false),
        Question("¿Si al juicio de expresidentes?", false),
        Question("¿Fue primero la gallina?", false),
        Question("¿La quesadilla lleva queso?", false)
    )

    private var currentQuestion =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id. question_text)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        next_Button = findViewById(R.id.next_button)

        questionTextView.setText(question[currentQuestion].strRestId)

        trueButton.setOnClickListener { view: View ->
            val result = if(question[currentQuestion].answer) "CORRECT0" else "INCORRECTO"
            Toast.makeText(
                 this,
                  result,
            Toast.LENGTH_SHORT
            ).show()
        }

        falseButton.setOnClickListener { view: View ->
            val result = if(question[currentQuestion].answer) "CORRECT0" else "INCORRECTO"
            Toast.makeText(
                this,
                result,
                Toast.LENGTH_SHORT
            ).show()
        }
        next_Button.setOnClickListener { view: View ->
           currentQuestion = (currentQuestion + 1) % question.size
            questionTextView.setText(question[currentQuestion].strRestId)


        }
    }
}