package com.example.quizapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textView_contador: TextView
    private lateinit var questionTextView: TextView
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var next_Button : Button
    private lateinit var previous_Button: Button
    private lateinit var textView_answers: TextView

    private val LOG_TAG = "MAINACTIVITY LOG"

    private val model: GameModel by viewModels()

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

        questionTextView.setText(getString(model.currentQuestion.strRestId))
        textView_contador.setText("${model.questionNumber+1}/${model.questionSize}")

        trueButton.setOnClickListener { view: View ->

            // si no está respondida la pregunta se guarda la respuesta en la lista.
            if(!model.currentAnswer.isAnswered) {
                model.currentAnswer.answer = true;
                model.currentAnswer.isAnswered = true;

                if(model.answersFinished()) {
                    // mostrar resultados
                    showResult();
                }

                val result = if (model.currentQuestion.answer) "CORRECT0" else "INCORRECTO"
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
            if(!model.currentAnswer.isAnswered) {
                model.currentAnswer.answer = false;
                model.currentAnswer.isAnswered = true;

                if(model.answersFinished()) {
                    // mostrar resultados
                    showResult();
                }

                val result = if (model.currentQuestion.answer) "CORRECT0" else "INCORRECTO"
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
            model.previousQuestion();
            textView_contador.setText("${model.questionNumber+1}/${model.questionSize}")
            questionTextView.setText(model.currentQuestion.strRestId)
        }

        next_Button.setOnClickListener { view: View ->
            model.nextQuestion();
            textView_contador.setText("${model.questionNumber+1}/${model.questionSize}")
            questionTextView.setText(model.currentQuestion.strRestId)
        }
      }

    fun showResult() {
        textView_answers.setText(model.getResult());
    }

override fun onStart(){
    super.onStart()
    Log.d(LOG_TAG, "onStart()...")
}

override fun onResume() {
    super.onResume()
    if(model.answersFinished()) {
        showResult();
    }
    Log.d(LOG_TAG, "onResume()...")
}

override fun onPause() {
    super.onPause()
    Log.d(LOG_TAG, "onPause()...")
}

override fun onStop() {
    super.onStop()
    Log.d(LOG_TAG, "onStop()...")
}

override fun onDestroy() {
    super.onDestroy()
    Log.d(LOG_TAG, "onDestroy()...")
}

    }
