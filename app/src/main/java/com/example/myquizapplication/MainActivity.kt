package com.example.myquizapplication

import android.graphics.Color
import android.icu.text.AlphabeticIndex
import android.icu.text.AlphabeticIndex.ImmutableIndex
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myquizapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val questions= arrayOf("Who won the first IPL trophy?",
        "Which player is called as Mr.IPL?",
        "How many times CSK won the IPL?")

    private val options= arrayOf(arrayOf("Rajasthan Royals","Deccan Chargers","Chennai Super Kings"),
            arrayOf("Virat Kohli","Suresh Raina","M.S Dhoni"),
            arrayOf("1","8","5"))
    private val correctAnswers= arrayOf(0,1,2)
    private var currentQuestionIndex =0
    private var score =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayQuestion()
        binding.AnsA.setOnClickListener { checkAnswers(0) }
        binding.AnsB.setOnClickListener { checkAnswers(1) }
        binding.AnsC.setOnClickListener { checkAnswers(2) }
        }
    private fun correctButtonColors(buttonIndex:Int) {
        when (buttonIndex) {
        0 -> binding.AnsA.setBackgroundColor(Color.GREEN)
        1 -> binding.AnsB.setBackgroundColor(Color.GREEN)
        2 -> binding.AnsC.setBackgroundColor(Color.GREEN)
        }
}
    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
        0 -> binding.AnsA.setBackgroundColor(Color.RED)
        1 -> binding.AnsB.setBackgroundColor(Color.RED)
        2 -> binding.AnsC.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColors(){
        binding.AnsA.setBackgroundColor(Color.WHITE)
        binding.AnsB.setBackgroundColor(Color.WHITE)
        binding.AnsC.setBackgroundColor(Color.WHITE)
    }
    private fun showResults(){
        Toast.makeText(this,"Your Score $score out of ${questions.size} ",Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled= true
    }
    private fun displayQuestion(){
        binding.questionText.text=questions[currentQuestionIndex]
        binding.AnsA.text=options[currentQuestionIndex][0]
        binding.AnsB.text=options[currentQuestionIndex][1]
        binding.AnsC.text=options[currentQuestionIndex][2]
            resetButtonColors()
    }
    private fun checkAnswers(selectedAnswerIndex:Int){
        val correctAnswerIndex=correctAnswers[currentQuestionIndex]
        if(selectedAnswerIndex==correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestionIndex<questions.size-1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()},1000)
        }else{
            showResults()
        }
    }
    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}