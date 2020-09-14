package com.example.quizapp

import androidx.lifecycle.ViewModel

class GameModel : ViewModel() {
    private var questionBank = listOf(
        Question(R.string.question_text_1, true),
        Question(R.string.question_text_2, false),
        Question(R.string.question_text_3, true),
        Question(R.string.question_text_4, false),
        Question(R.string.question_text_5, false),
        Question(R.string.question_text_6, false),
        Question(R.string.question_text_7, false),
        Question(R.string.question_text_8, false),
        Question(R.string.question_text_9, false),
        Question(R.string.question_text_10, false)

    )

    private var answerBank = listOf(
    UserAnswer(R.string.question_text_1, false, false),
    UserAnswer(R.string.question_text_2, false, false),
    UserAnswer(R.string.question_text_3, false, false),
    UserAnswer(R.string.question_text_4, false, false),
    UserAnswer(R.string.question_text_5, false, false),
    UserAnswer(R.string.question_text_6, false, false),
    UserAnswer(R.string.question_text_7, false, false),
    UserAnswer(R.string.question_text_8, false, false),
    UserAnswer(R.string.question_text_9, false, false),
    UserAnswer(R.string.question_text_10, false, false)
    )

    private var currentIndex = 0

    val currentQuestion: Question
        get() = questionBank[currentIndex]

    val currentAnswer: UserAnswer
        get() = answerBank[currentIndex]

    val questionSize: Int
        get() = questionBank.size

    val questionNumber: Int
        get() = currentIndex

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun previousQuestion() {
        if(currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = 0;
        }
    }

    fun answersFinished() : Boolean {
        for(answer in answerBank) {
            if(!answer.isAnswered) {
                return false;
            }
        }
        return true;
    }

    fun getResult(): String {
        var correct_answers : Int = 0;
        var cont : Int = 0;

        for(answer in answerBank) {
            if(answer.answer == questionBank[cont].answer) {
                correct_answers++;
            }
            cont++;
        }

        return "$correct_answers/${answerBank.size} ${correct_answers*10}PTS";
    }

    override fun onCleared() {
        super.onCleared()
        //Log.d()
    }
}
