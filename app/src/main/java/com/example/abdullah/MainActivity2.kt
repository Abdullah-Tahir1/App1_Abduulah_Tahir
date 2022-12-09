package com.example.abdullah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Intent
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Intent variables
        val intent=getIntent()
        val currentDate=intent.getIntExtra("CurrentDate",0)
        val currentMonth=intent.getIntExtra("CurrentMonth",0)
        val currentYear=intent.getIntExtra("CurrentYear",0)
        val birthDate=intent.getIntExtra("BirthDate",0)
        val birthMonth=intent.getIntExtra("BirthMonth",0)
        val birthYear=intent.getIntExtra("BirthYear",0)
        //Extra Variables
        var monthNames = arrayOf("January","February","March","April","May","June","July","August","September","October","November","December")
        val wholeCurrentDate = findViewById<TextView>(R.id.currentDate)
        wholeCurrentDate.setText("$currentDate " + monthNames[currentMonth] + " $currentYear")
        val wholeBirthDate = findViewById<TextView>(R.id.birthDate)
        wholeBirthDate.setText("$birthDate " + monthNames[birthMonth] + " $currentYear")
        var monthDays = arrayOf(31,28,31,30,31,30,31,31,30,31,30,31)
        val months=findViewById<TextView>(R.id.months)
        var monthRatio = 4.34649122807017543859649
        //Calling Animations
        val animationFadeInUp = AnimationUtils.loadAnimation(this,R.anim.fade_in_from_down)
        val animationBlink = AnimationUtils.loadAnimation(this,R.anim.blink)
        val animationSlide = AnimationUtils.loadAnimation(this,R.anim.slide)
        //Necessary Calculations
        var monthsLived = (((currentYear - birthYear) - 1) * 12) + (11 - birthMonth) + (currentMonth)
        var weeksLived = 1 + (monthsLived * monthRatio) + (((monthDays[currentMonth] - birthDate) - ((monthDays[currentMonth] - birthDate) % 7)) /7) + ((currentDate - (currentDate % 7)) / 7)
        var daysLived = ((weeksLived * 7) + 1).toInt()
        //Starting Animations
        underline1.startAnimation(animationBlink)
        underline2.startAnimation(animationBlink)
        underline3.startAnimation(animationBlink)
        underline4.startAnimation(animationBlink)
        underline5.startAnimation(animationBlink)
        underline6.startAnimation(animationBlink)
        months.startAnimation(animationFadeInUp)
        weeks.startAnimation(animationFadeInUp)
        days.startAnimation(animationFadeInUp)
        minutes.startAnimation(animationFadeInUp)
        seconds.startAnimation(animationFadeInUp)
        years.startAnimation(animationFadeInUp)
        HeadingUnderline.startAnimation(animationSlide)
        //Edit Buttons
        birthDateEdit.setOnClickListener{
            val intent = Intent(this@MainActivity2,MainActivity4::class.java)
            val checker = 1
            var dialougChecker = 1
            intent.putExtra("dialougChecker",dialougChecker)
            intent.putExtra("CurrentDate",currentDate)
            intent.putExtra("CurrentMonth",currentMonth)
            intent.putExtra("CurrentYear",currentYear)
            intent.putExtra("Checker",checker)
            startActivity(intent)
        }
        currentDateEdit2.setOnClickListener{
            val intent = Intent(this@MainActivity2,MainActivity3::class.java)
            var dialougChecker = 1
            intent.putExtra("dialougChecker",dialougChecker)
            startActivity(intent)
        }
        //Printing out the results
        months.text="You have lived :\n $monthsLived months"
        weeks.text="You have lived :\n ${weeksLived.toInt()} weeks"
        days.text="You have lived :\n ${daysLived} days"
        minutes.text="You have lived :\n ${daysLived*1440} minutes"
        seconds.text="You have lived :\n ${1440*3600*daysLived.toLong()} seconds"
        if (birthMonth > currentMonth){
            years.text="You have lived :\n ${currentYear - birthYear} years"
        }
        else if (birthMonth < currentMonth){
            years.text="You have lived :\n ${(currentYear - birthYear)-1} years"
        }
        else if (birthMonth == currentMonth){
            if (birthDate < currentDate){
                years.text="You have lived :\n ${currentYear - birthYear} years"
            }
            else if(birthDate == currentDate){
                years.text="You have lived :\n ${currentYear - birthYear} years"
                birthdayText.text = "Happy Birthday"
            }
            else if (birthDate > currentDate){
                years.text="You have lived :\n ${(currentYear - birthYear)-1} years"
            }
        }
    }
}