package com.example.guessthebuttonv2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
   lateinit var buttons:Array<Button>
   var score=0
    var randomNr=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        var start=findViewById<Button>(R.id.start_button)
        buttons=Array(6){i->
            initButtons(i)
        }
        start.setOnClickListener { startGame() }
    }



    fun initButtons(i:Int):Button{
        val buttons:Button=findViewById(resources.getIdentifier("button$i","id",packageName))
        buttons.setVisibility(View.INVISIBLE)
        return buttons

    }

    fun startGame(){
        var aux=findViewById<EditText>(R.id.number_text).text.toString()
        if(aux.length==0 || aux.length>1 || aux.toInt()>6 || aux.toInt()<2){
            Toast.makeText(this, "Enter valid number", Toast.LENGTH_SHORT).show();
            return
        }else {
            var counter = aux.toInt()
            randomNr=(0..counter-1).random()
                for (i in 0..counter - 1) {
                    buttons[i].setVisibility(View.VISIBLE)
                    buttons[i].setOnClickListener { clk(i) }
                }

        }
    }

    fun clk(x:Int) {
        var ok = true
        if (x == randomNr) {
            resetGame()
        } else {
            ok=false
            buttons[x].setVisibility(View.INVISIBLE)
        }
        if (ok) {
            score += 1
        } else{
            score -= 1
    }
        findViewById<TextView>(R.id.score).text="Your score:$score"

    }

    fun resetGame(){
        randomNr=(0..5).random()
        for(i in 0..5){
            buttons[i].setVisibility(View.INVISIBLE)
        }
    }


}