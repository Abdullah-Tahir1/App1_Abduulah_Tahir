package com.example.abdullah

import android.annotation.SuppressLint
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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main4.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity4 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    fun nextPage(Currentdate: Int,Currentmonth: Int,Currentyear:Int,Birthdate: Int,Birthmonth: Int,Birthyear:Int){
        val intent = Intent(this@MainActivity4,MainActivity2::class.java)
        intent.putExtra("CurrentDate",Currentdate)
        intent.putExtra("CurrentMonth",Currentmonth)
        intent.putExtra("CurrentYear",Currentyear)
        intent.putExtra("BirthDate",Birthdate)
        intent.putExtra("BirthMonth",Birthmonth)
        intent.putExtra("BirthYear",Birthyear)
        startActivity(intent)
    }
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        //Intent variables
        val intent=getIntent()
        val checker = intent.getIntExtra("Checker",0)
        val now = LocalDateTime.now()
        var dateFormatter = DateTimeFormatter.ofPattern("dd")
        var monthFormatter = DateTimeFormatter.ofPattern("MM")
        var yearFormatter = DateTimeFormatter.ofPattern("yyyy")
        var currentDate=intent.getIntExtra("CurrentDate",0)
        var currentMonth=intent.getIntExtra("CurrentMonth",0)
        var currentYear=intent.getIntExtra("CurrentYear",0)
        if (checker == 0){
            currentDate = dateFormatter.format(now).toString().toInt()
            currentMonth = monthFormatter.format(now).toString().toInt()-1
            currentYear = yearFormatter.format(now).toString().toInt()
        }
        //Extra Variables
        val wholeCurrentDate = findViewById<TextView>(R.id.currentDate)
        val underline = findViewById<TextView>(R.id.HeadingUnderline4)
        val currentdate = findViewById<TextView>(R.id.currentDate)
        val dateAnimation = findViewById<EditText>(R.id.dateInputBirthDate)
        val mont = findViewById<TextView>(R.id.newMonthsBirthDate)
        var months = arrayOf("January","February","March","April","May","June","July","August","September","October","November","December")
        wholeCurrentDate.setText("$currentDate " + months[currentMonth] + " $currentYear")
        var i = -1
        var dayIncrement = 0
        val yearCounter = findViewById<EditText>(R.id.yearInputBirthDate)
        var dialougChecker = intent.getIntExtra("dialougChecker",0)
        //Calling Buttons by their IDs
        val datePlusButton = findViewById<Button>(R.id.datePlusBtnBirthDate)
        val dateMinusButton = findViewById<Button>(R.id.dateMinusBtnBirthDate)
        val monthPlusButton = findViewById<Button>(R.id.monthPlusBtnBirthDate)
        val monthMinusButton = findViewById<Button>(R.id.monthMinusBtnBirthDate)
        val ansBtn = findViewById<Button>(R.id.btnBirthDate)
        //Calling Animations
        val fadeInFromLeftAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_left)
        val fadeInFromRightAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_right)
        val FadeInFromDown = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_down)
        val animationSlide = AnimationUtils.loadAnimation(this,R.anim.slide)
        //Starting Animation
        datePlusButton.startAnimation(fadeInFromLeftAnimation)
        dateMinusButton.startAnimation(fadeInFromRightAnimation)
        monthPlusButton.startAnimation(fadeInFromLeftAnimation)
        monthMinusButton.startAnimation(fadeInFromRightAnimation)
        dateAnimation.startAnimation(FadeInFromDown)
        mont.startAnimation(FadeInFromDown)
        yearCounter.startAnimation(FadeInFromDown)
        underline.startAnimation(animationSlide)
        ansBtn.startAnimation(FadeInFromDown)
        currentdate.startAnimation(fadeInFromLeftAnimation)
        //dialoug
        if(dialougChecker == 0){
            val myDialoug = Dialog(this)
            myDialoug.setContentView(R.layout.custom_dialoug)
            myDialoug.setCancelable(true)
            myDialoug.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialoug.show()
        }
        currentDateEdit1.setOnClickListener{
            val intent = Intent(this@MainActivity4,MainActivity3::class.java)
            startActivity(intent)
        }
        //Date Plus Button Click Listener
        datePlusBtnBirthDate.setOnClickListener{
           val dateCounter1 = findViewById<EditText>(R.id.dateInputBirthDate)
           dayIncrement = dateCounter1.text.toString().toInt()+1
           dateCounter1.setText(dayIncrement.toString())
       }
        //Date Minus Button Click Listener
        dateMinusBtnBirthDate.setOnClickListener{
           val dateCounter1 = findViewById<EditText>(R.id.dateInputBirthDate)
           var dayDecrement = dateCounter1.text.toString().toInt()-1
           if (dayDecrement > 0)
           {
               dateCounter1.setText(dayDecrement.toString())
           }
       }
        //Month Plus Button Click Listener
        monthPlusBtnBirthDate.setOnClickListener{
           i+=1
           if (i > 11)
           {
               i = 0
           }
           var monthIncrement = months[i]
           mont.setText(monthIncrement)
       }
        //Month Minus Button Click Listener
        monthMinusBtnBirthDate.setOnClickListener{
           i-=1
           if (i < 0)
           {
               i = 11
           }
           var monthDecrement = months[i]
           mont.setText(monthDecrement)
       }
       btnBirthDate.setOnClickListener{
           val dateCounter2 = findViewById<EditText>(R.id.dateInputBirthDate).text.toString().toInt()
           if (dateCounter2 > 31 || dateCounter2 < 1){
               Toast.makeText(this, "$dateCounter2 isn't a valid date", Toast.LENGTH_SHORT).show()
           }
           else if(i < 0){
               Toast.makeText(this, "Enter a valid month",Toast.LENGTH_SHORT).show()
           }
           else if(dateCounter2 > 30 && (i == 3 || i == 5 || i == 8 || i == 10)){
               Toast.makeText(this, "$dateCounter2 isn't a valid date in " + months[i], Toast.LENGTH_SHORT).show()
           }
           else if (i == 1 && (dateCounter2 == 29 || dateCounter2 == 30 || dateCounter2 == 31 )){
               Toast.makeText(this, "$dateCounter2 isn't a valid date in " + months[i], Toast.LENGTH_SHORT).show()
           }
           else if(yearCounter.text.toString().toInt() < 0){
               Toast.makeText(this, "$yearCounter isn't a valid year", Toast.LENGTH_SHORT).show()
           }
           else if(yearCounter.text.toString().toInt() >= currentYear){
               if (yearCounter.text.toString().toInt() > currentYear) {
                   Toast.makeText(
                       this,
                       "Your Birth year is higher than current year",
                       Toast.LENGTH_SHORT
                   ).show()
               }
               else if (yearCounter.text.toString().toInt() == currentYear) {
                   if (i > currentMonth){
                       Toast.makeText(this, "Your Birth month is higher than current month", Toast.LENGTH_SHORT).show()
                   }
                   else if (i == currentMonth){
                       if (dateCounter2 > currentDate){
                           Toast.makeText(this, "Your Birth date is higher than current date", Toast.LENGTH_SHORT).show()
                       }
                       else{
                           nextPage(currentDate,currentMonth,currentYear,dateCounter2,i,yearCounter.text.toString().toInt())
                       }
                   }
                   else{
                       nextPage(currentDate,currentMonth,currentYear,dateCounter2,i,yearCounter.text.toString().toInt())
                   }
               }
               else{
                   nextPage(currentDate,currentMonth,currentYear,dateCounter2,i,yearCounter.text.toString().toInt())
               }
           }
           else{
               nextPage(currentDate,currentMonth,currentYear,dateCounter2,i,yearCounter.text.toString().toInt())

           }
           //Toast.makeText(this, "Selected item: ",Toast.LENGTH_LONG).show()
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