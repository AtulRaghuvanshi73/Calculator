package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? =  null
    var lastnumeric:Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun numClicked(view: View){
        tvInput?.append((view as Button).text)
        lastnumeric = true
        lastDot = false
    }

    fun Onclr(view: View){
        tvInput?.text = ""
    }

    fun onDecimalPoint(view:View){
        if(lastnumeric && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastnumeric = false
        }
    }

    fun onOperator(view : View){

        tvInput?.text?.let{
            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastnumeric = false
                lastDot = false
            }
        }

    }
    fun onEqual(view: View){
      if(lastnumeric){
          var tvValue = tvInput?.text.toString()
          var prefix = ""
          try{
              if(tvValue.startsWith("-")){
                  tvValue = tvValue.substring(1)//it starts from 1 instead of 0
              }
              if(tvValue.contains("-")){
                  val splitvalue = tvValue.split("-")
                  var one = splitvalue[0]
                  var two = splitvalue[1]

                  if(prefix.isNotEmpty()){
                      one += prefix
                  }
                  //using a var to do the same thing
                  //var result = one.toDouble() - two.toDouble()
                  //then converting this to a string using toString() method -> result.toString()
                  tvInput?.text =  zeroRemover((one.toDouble() - two.toDouble()).toString())//could have been done with a seperate variable
              }else  if(tvValue.contains("+")){
                  val splitvalue = tvValue.split("-")
                  var one = splitvalue[0]
                  var two = splitvalue[1]

                  if(prefix.isNotEmpty()){
                      one += prefix
                  }
                  tvInput?.text =  zeroRemover((one.toDouble() + two.toDouble()).toString())
              }else  if(tvValue.contains("/")){
                  val splitvalue = tvValue.split("-")
                  var one = splitvalue[0]
                  var two = splitvalue[1]

                  if(prefix.isNotEmpty()){
                      one += prefix
                  }
                  tvInput?.text =  zeroRemover((one.toDouble() / two.toDouble()).toString())
              }else  if(tvValue.contains("*")){
                  val splitvalue = tvValue.split("-")
                  var one = splitvalue[0]
                  var two = splitvalue[1]

                  if(prefix.isNotEmpty()){
                      one += prefix
                  }
                  tvInput?.text = zeroRemover((one.toDouble() * two.toDouble()).toString())
              }



          }catch (e: ArithmeticException){
              e.printStackTrace()//it will show the error in the logcat section, if there is any
          }

      }
    }

    private fun zeroRemover(result:String):String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length - 2)
        }

        return value
    }

    private fun isOperatorAdded(value :String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("+")
                    ||value.contains("*")
                    ||value.contains("-")
        }

    }

}