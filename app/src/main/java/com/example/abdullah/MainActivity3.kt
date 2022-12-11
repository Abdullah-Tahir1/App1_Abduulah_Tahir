package com.example.abdullah

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main3.*
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    fun nextPage(date: Int,month: Int,year:Int){
        val intent = Intent(this@MainActivity3,MainActivity4::class.java)
        val checker = 1
        var dialougChecker = 1
        intent.putExtra("CurrentDate",date)
        intent.putExtra("CurrentMonth",month)
        intent.putExtra("CurrentYear",year)
        intent.putExtra("Checker",checker)
        intent.putExtra("dialougChecker",dialougChecker)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        //Dialoug Box
            //displayDialoug()
        //Extra Variables
        val underline = findViewById<TextView>(R.id.HeadingUnderline3)
        val mont = findViewById<TextView>(R.id.newMonthsCurrentDate)
        val dateAnimation = findViewById<EditText>(R.id.dateInputCurrentDate)
        var months = arrayOf("January","February","March","April","May","June","July","August","September","October","November","December")
        var i = -1
        var dayIncrement = 0
        val yearCounter = findViewById<EditText>(R.id.yearInputCurrentDate)
       //Calling Buttons by their IDs
        val dateMinusButton = findViewById<Button>(R.id.dateMinusBtnCurrentDate)
        val datePlusButton = findViewById<Button>(R.id.datePlusBtnCurrentDate)
        val monthMinusButton = findViewById<Button>(R.id.monthMinusBtnCurrentDate)
        val monthPlusButton = findViewById<Button>(R.id.monthPlusBtnCurrentDate)
        val ansBtn = findViewById<Button>(R.id.btnCurrentDate)
        //Calling Animations
        val fadeInFromLeftAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_left)
        val fadeInFromRightAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_right)
        val FadeInFromDown = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_down)
        val animationSlide = AnimationUtils.loadAnimation(this,R.anim.slide)
        //Starting Animation
        dateMinusButton.startAnimation(fadeInFromLeftAnimation)
        datePlusButton.startAnimation(fadeInFromRightAnimation)
        monthMinusButton.startAnimation(fadeInFromLeftAnimation)
        monthPlusButton.startAnimation(fadeInFromRightAnimation)
        dateAnimation.startAnimation(FadeInFromDown)
        mont.startAnimation(FadeInFromDown)
        yearCounter.startAnimation(FadeInFromDown)
        underline.startAnimation(animationSlide)
        ansBtn.startAnimation(FadeInFromDown)
        //Date Plus Button Click Listener
        dateMinusBtnCurrentDate.setOnClickListener{
            val dateCounter1 = findViewById<EditText>(R.id.dateInputCurrentDate)
            var dayDecrement = dateCounter1.text.toString().toInt()-1
            if (dayDecrement > 0)
            {
                dateCounter1.setText(dayDecrement.toString())
            }
            else if (dayDecrement < 1){
                Toast.makeText(this, "Negative dates aren't allowed", Toast.LENGTH_SHORT).show()
            }
        }
        //Date Minus Button Click Listener
        datePlusBtnCurrentDate.setOnClickListener{
            val dateCounter1 = findViewById<EditText>(R.id.dateInputCurrentDate)
            var dayIncrement = dateCounter1.text.toString().toInt()+1
            if (dayIncrement < 32)
            {
                dateCounter1.setText(dayIncrement.toString())
            }
            else if (dayIncrement > 31){
                Toast.makeText(this, "Dates above 31 aren't allowed", Toast.LENGTH_SHORT).show()
            }
        }
        //Month Plus Button Click Listener
        monthMinusBtnCurrentDate.setOnClickListener{
            i-=1
            if (i < 0)
            {
                i = 11
            }
            var monthIncrement = months[i]
            mont.setText(monthIncrement)
        }
        //Month Minus Button Click Listener
        monthPlusBtnCurrentDate.setOnClickListener{
            i+=1
            if (i > 11)
            {
                i = 0
            }
            var monthDecrement = months[i]
            mont.setText(monthDecrement)
        }
        btnCurrentDate.setOnClickListener{
            val dateCounter2 = findViewById<EditText>(R.id.dateInputCurrentDate).text.toString().toInt()
            if (dateCounter2 > 31 || dateCounter2 < 1){
                Toast.makeText(this, "$dateCounter2 isn't a valid date",Toast.LENGTH_SHORT).show()
            }
            else if(i < 0){
                Toast.makeText(this, "Enter a valid month",Toast.LENGTH_SHORT).show()
            }
            else if(dateCounter2 > 30 && (i == 3 || i == 5 || i == 8 || i == 10)){
                Toast.makeText(this, "$dateCounter2 isn't a valid date in " + months[i],Toast.LENGTH_SHORT).show()
            }
            else if (i == 1 && (dateCounter2 == 29 || dateCounter2 == 30 || dateCounter2 == 31 )){
                Toast.makeText(this, "$dateCounter2 isn't a valid date in " + months[i],Toast.LENGTH_SHORT).show()
            }
            else if(yearCounter.text.toString().toInt() < 0){
                Toast.makeText(this, "$yearCounter isn't a valid year",Toast.LENGTH_SHORT).show()
            }
            else{
                nextPage(dateCounter2,i,yearCounter.text.toString().toInt())
            }
        }
    }
    fun displayDialoug(view: View){
        val myDialoug = Dialog(this)
        myDialoug.setContentView(R.layout.custom_dialoug)
        myDialoug.setCancelable(true)
        myDialoug.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialoug.show()
    }
}


