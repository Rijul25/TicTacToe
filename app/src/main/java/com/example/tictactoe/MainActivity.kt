 package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() , View.OnClickListener{

     var Player = true
     var Turn_Count = 0

     var board_status = Array(3){IntArray(3)}


    lateinit var board : Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)


        )
        for(i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }

        InitialiseBoardStatus()

        ResetBtn.setOnClickListener {
            Player = true
            Turn_Count = 0
            InitialiseBoardStatus()

        }
    }

     private fun InitialiseBoardStatus() {
         // we will be initilising the array with -1
         for(i in 0..2) {
             for (j in 0..2) {
                 board_status[i][j] = -1
             }
         }
         for(i in board){
             for (button in i){
                 button.isEnabled = true
                 button.text = ""

             }
         }
     }

     override fun onClick(view: View) {
         when(view.id){
             R.id.button ->{
                 updateValue(row = 0 , col = 0 , player = Player)

             }
             R.id.button2 ->{
                 updateValue(row = 0 , col = 1 , player = Player)

             }
             R.id.button3 ->{
                 updateValue(row = 0 , col = 2 , player = Player)

             }
             R.id.button4 ->{
                 updateValue(row = 1 , col = 0 , player = Player)

             }
             R.id.button5 ->{
                 updateValue(row = 1 , col = 1 , player = Player)

             }
             R.id.button6 ->{
                 updateValue(row = 1 , col = 2 , player = Player)

             }
             R.id.button7 ->{
                 updateValue(row = 2 , col = 0 , player = Player)

             }
             R.id.button8 ->{
                 updateValue(row = 2 , col = 1 , player = Player)

             }
             R.id.button9 ->{
                 updateValue(row = 2 , col = 2 , player = Player)

             }
         }
         Turn_Count++
         Player = !Player

         if(Player){
             updateDisplay("Player X Turn")
         }
         else{
             updateDisplay("Player O Turn")
         }

         if(Turn_Count == 9){
             updateDisplay("Game Draw")
         }
         checkWinner()
     }

     private fun checkWinner() {
         //horizontal rows
         for (i in 0..2){
             if(board_status[i][0] == board_status[i][1] && board_status[i][0] == board_status[i][2]){
                 //check if player 1 or 2
                 if(board_status[i][0] == 1){
                     updateDisplay("Player X is Winner")
                     break
                 }else if(board_status[i][0] == 0){
                     updateDisplay("Player O is Winner")
                     break
                 }
             }
         }
         //vertical
         for (i in 0..2){
             if(board_status[0][i] == board_status[1][i] && board_status[0][i] == board_status[2][i]){
                 //check if player 1 or 2
                 if(board_status[0][i] == 1){
                     updateDisplay("Player X is Winner")
                     break
                 }else if(board_status[0][i] == 0){
                     updateDisplay("Player O is Winner")
                     break
                 }
             }
         }

         //first diagonal
         for (i in 0..2){
             if(board_status[0][0] == board_status[1][1] && board_status[0][i] == board_status[2][2]){
                 if(board_status[0][0] == 1){
                     updateDisplay("Player X is Winner")

                 }else if(board_status[0][0] == 0){
                     updateDisplay("Player O is Winner")
                 }
             }
         }


         //second diagonal
         for (i in 0..2){
             if(board_status[0][2] == board_status[1][1] && board_status[0][0] == board_status[2][0]){
                 if(board_status[0][2] == 1){
                     updateDisplay("Player X is Winner")

                 }else if(board_status[0][2] == 0){
                     updateDisplay("Player O is Winner")
                 }
             }
         }
     }

     private fun updateDisplay(text: String) {
         displayTv.text = text
        //check if winner kahi hai to , button disable kardo
         if(text.contains("Winner")){
             disableButton()
         }
     }


     private fun disableButton(){
         for(i in board){
             for (button in i){
                 button.isEnabled = false
             }
         }
     }

     private fun updateValue(row: Int, col: Int, player: Boolean) {
         //this will change the text value of board , and we will also have to disable it
         val text:String = if(player) "X" else "O" //if player 1 hai then X else 0
         val value:Int = if(player) 1 else 0

         board[row][col].apply {
             isEnabled = false // taaki dobara click na ho
             setText(text)

         }

         board_status[row][col] = value //update the status of board


     }
 }