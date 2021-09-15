package com.keleshteri.quizandroidapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.keleshteri.quizandroidapp.databinding.ActivityMainBinding
import com.keleshteri.quizandroidapp.databinding.ActivityQuizQuestionsBinding
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), OnClickListener {

    //binding
    private lateinit var binding: ActivityQuizQuestionsBinding

    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? =null
    private var mSelectedOptionPosition:Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_quiz_questions)
        setContentView(binding.root)

        //get all Questions
        mQuestionsList = Constants.getQuestions()

        setQuestion()

        //onclick
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)



    }

    /**
     * set Question
     */
    private fun setQuestion(){
        /**  **/
        mCurrentPosition=1
        val question = mQuestionsList!![mCurrentPosition-1]

        //set default style for options
        defaultOptionsView()


        binding.progressBar.progress= mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/"+binding.progressBar.max

        binding.tvQuestion.text = question!!.question
        binding.ivImage.setImageResource(question.image)

        binding.tvOptionOne.text=question.optionOne
        binding.tvOptionTwo.text=question.optionTwo
        binding.tvOptionThree.text=question.optionThree
        binding.tvOptionFour.text =question.optionFour
    }

    /**
     * set default style for options
     */
    private fun defaultOptionsView(){
        // create array list TextView
        val options = ArrayList<TextView>()

        //added from activity_quiz_questions layout
        options.add(0,binding.tvOptionOne)
        options.add(1,binding.tvOptionTwo)
        options.add(2,binding.tvOptionThree)
        options.add(3,binding.tvOptionFour)

        //for Loop
        for (option in options){
            //set all style
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface =Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )

        }


    }

    /**
     * Selected Options
     */
    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        //rest all style all options
        defaultOptionsView()

        mSelectedOptionPosition= selectedOptionNum

        //set style for selected option
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )


    }

    /**
     * Handle all OnClick in view
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(binding.tvOptionOne,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(binding.tvOptionTwo,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(binding.tvOptionThree,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(binding.tvOptionFour,4)
            }
        }
    }
}