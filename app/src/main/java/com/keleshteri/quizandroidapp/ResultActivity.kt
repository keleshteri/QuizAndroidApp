package com.keleshteri.quizandroidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.keleshteri.quizandroidapp.databinding.ActivityMainBinding
import com.keleshteri.quizandroidapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    //
    private lateinit var binding:ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_result)
        setContentView(binding.root)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        val username = intent.getStringExtra(Constants.USER_NAME)
        binding.tvName.text=username

        val totalQuestions  =intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        binding.tvScore.text="Your Score is $correctAnswers out of $totalQuestions"

        //button
        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}