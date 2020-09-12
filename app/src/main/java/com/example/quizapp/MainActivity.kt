package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textView_contador: TextView
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var next_Button : Button
    private lateinit var previous_Button: Button
    private lateinit var textView_answers: TextView


    private val question = listOf<Question>(
        Question("¿La luna es de queso?", false),
        Question("¿El cielo realmente es azul?", true),
        Question("¿La tierra es plana?", false),
        Question("¿Si al juicio de expresidentes?", true),
        Question("¿Fue primero la gallina?", true),
        Question("¿La quesadilla lleva queso?", true),
        Question("¿Los gatos toman leche?", true),
        Question("¿Todas las serpientes son venenosas?", false),
        Question("¿Los arabes comen kibis?", false),
        Question("¿Los daltonicos son inteligentes?", true)




    )

    private val answers = listOf<UserAnswer>(
        UserAnswer("¿La luna es de queso?", false, false),
        UserAnswer("¿El cielo realmente es azul?", false, false),
        UserAnswer("¿La tierra es plana?", false, false),
        UserAnswer("¿Si al juicio de expresidentes?", false, false),
        UserAnswer("¿Fue primero la gallina?", false, false),
        UserAnswer("¿La quesadilla lleva queso?", false, false),
        UserAnswer("¿Los gatos toman leche?", false, false),
        UserAnswer("¿Todas las serpientes son venenosas?", false, false),
        UserAnswer("¿Los arabes comen kibis?", false, false),
        UserAnswer("¿Los daltonicos son inteligentes?", false, false)
    )
    private var currentQuestion =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView_contador = findViewById(R.id.textView_contador)
        questionTextView = findViewById(R.id.question_text)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        next_Button = findViewById(R.id.next_button)
        previous_Button = findViewById(R.id.previous_button)
        textView_answers =findViewById(R.id.textView_answers)

        questionTextView.setText(question[currentQuestion].strRestId)

        trueButton.setOnClickListener { view: View ->

            // si no está respondida la pregunta se guarda la respuesta en la lista.
            if(!answers[currentQuestion].isAnswered) {
                answers[currentQuestion].answer = true;
                answers[currentQuestion].isAnswered = true;

                textView_contador.setText("${currentQuestion+1}/${answers.size}")

                if(answersFinished()) {
                    // mostrar resultados
                    showResult();
                }

                val result = if (question[currentQuestion].answer) "CORRECT0" else "INCORRECTO"
                Toast.makeText(
                    this,
                    result,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "ESTA PREGUNTA YA HA SIDO RESPONDIDA",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        falseButton.setOnClickListener { view: View ->
            // si no está respondida la pregunta se guarda la respuesta en la lista.
            if(!answers[currentQuestion].isAnswered) {
                answers[currentQuestion].answer = false;
                answers[currentQuestion].isAnswered = true;

                if(answersFinished()) {
                    // mostrar resultados
                    showResult();
                }

                val result = if (question[currentQuestion].answer) "CORRECT0" else "INCORRECTO"
                Toast.makeText(
                    this,
                    result,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "ESTA PREGUNTA YA HA SIDO RESPONDIDA",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        previous_Button.setOnClickListener { view: View ->
            currentQuestion = (currentQuestion - 1) % question.size
            questionTextView.setText(question[currentQuestion].strRestId)
        }

        next_Button.setOnClickListener { view: View ->
            currentQuestion = (currentQuestion + 1) % question.size
            questionTextView.setText(question[currentQuestion].strRestId)
        }
      }

    fun answersFinished() : Boolean {
        for(answer in answers) {
            if(!answer.isAnswered) {
                return false;
            }
        }
        return true;
    }

    fun showResult() {
        var correct_answers : Int;
        var cont : Int;
        correct_answers = 0;
        cont = 0;

        for(answer in answers) {
            if(answer.answer == question[cont].answer) {
                correct_answers++;
            }
            cont++;
        }

        textView_answers.setText("$correct_answers/${answers.size} ${correct_answers*10}PTS");

    }

    }
