package com.example.wordleproject
import FourLetterWordList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Parameters / Fields:
 *   wordToGuess : String - the target word the user is trying to guess
 *   guess : String - what the user entered as their guess
 *
 * Returns a String of 'O', '+', and 'X', where:
 *   'O' represents the right letter in the right place
 *   '+' represents the right letter in the wrong place
 *   'X' represents a letter not in the target word
 */
private fun checkGuess(guess: String,wordToGuess: String) : String {
    var result = ""
    for (i in 0..3) {
        if (guess[i] == wordToGuess[i]) {
            result += "O"
        }
        else if (guess[i] in wordToGuess) {
            result += "+"
        }
        else {
            result += "X"
        }
    }
    return result
}
private fun checkWin(checkedGuess:String) : Boolean{
    var result = true
    for (i in 0..3){
        if(checkedGuess[i] != 'O'){
            result = false
            break
        }
    }
    //Return the final result
    return result
}



class MainActivity : AppCompatActivity() {
    var fourLetterWord : String = FourLetterWordList.getRandomFourLetterWord()
    var counter : Int = 0
    var validationString : String=""
    var userInput : String= ""
    var validatedInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        val submitButton = findViewById<Button>(R.id.button2)
        val restartButton = findViewById<Button>(R.id.button3)
        val guessInput = findViewById<EditText>(R.id.guessInput)
        val textView = findViewById<TextView>(R.id.textView3)

        //Guess TextView
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)

        //Guess Check TextViews
        val guessCheck1 = findViewById<TextView>(R.id.guess1Check)
        val guessCheck2 = findViewById<TextView>(R.id.guess2Check)
        val guessCheck3 = findViewById<TextView>(R.id.guess3Check)

        //validation
        textView.text = fourLetterWord.toString()
        submitButton.setOnClickListener{
            userInput = guessInput.text.toString()
            if (userInput.length < 4){
                Toast.makeText(it.context,"Invalid In",Toast.LENGTH_SHORT).show()
            }
            else{
                counter++;
                //Submit the current 4 letter 4 and check for the guess
                validationString = checkGuess(userInput.toString().uppercase(),fourLetterWord)
                if (counter == 1){
                    guess1.text = userInput.uppercase()
                    //Checking for the text
                    guessCheck1.text = validationString
                }
                else if (counter == 2){
                    guess2.text = userInput.uppercase()
                    //Checking for the text
                    guessCheck2.text = validationString
                }
                else if (counter == 3){
                    guess3.text = userInput.uppercase()
                    guessCheck3.text = validationString
                    submitButton.visibility = View.INVISIBLE
                    restartButton.visibility = View.VISIBLE
                }
                //reseting the input stream
                guessInput.setText(null)
                if(checkWin(validationString)){

                    Toast.makeText(it.context,"YOU WON",Toast.LENGTH_SHORT).show()
                    submitButton.visibility = View.INVISIBLE
                    restartButton.visibility = View.VISIBLE
                    restartButton.setText("START A NEW GAME")
                }
            }
        }
        restartButton.setOnClickListener{
            Toast.makeText(it.context,"Restarting the Game", Toast.LENGTH_SHORT).show()
            counter = 0
            Toast.makeText(it.context,"Generating new Word", Toast.LENGTH_SHORT).show()
            fourLetterWord = FourLetterWordList.getRandomFourLetterWord()
            textView.text = fourLetterWord
            submitButton.visibility = View.VISIBLE
            restartButton.visibility = View.INVISIBLE
            //clear all of the fields
            guess1.text=null
            guess2.text=null
            guess3.text=null

            guessCheck1.text=null
            guessCheck2.text=null
            guessCheck3.text=null

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}