package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startGame,button0,button1,button2,button3,playAgain;
    TextView currentScore,currentQuestion,timeLeft,answer;
    ArrayList<Integer> listOfQuestions = new ArrayList<Integer>();
    int firstNumber,secondNumber,result,correctPosition;
    int score,questionNumber;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame.setVisibility(View.INVISIBLE);
                currentQuestion.setVisibility(View.VISIBLE);
                currentScore.setVisibility(View.VISIBLE);
                timeLeft.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button0.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                letsStartTheGame();
                timer();
            }
        });
    }

    private void timer() {
        new CountDownTimer(45010,1000){

            @Override
            public void onTick(long l) {
                timeLeft.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                gameEnded();
            }
        }.start();
    }

    private void gameEnded() {
        timeLeft.setText("0s");
        answer.setText("Your Score is " + currentScore.getText().toString());
        playAgain.setVisibility(View.VISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letsStartTheGame();
                button1.setVisibility(View.VISIBLE);
                button0.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                answer.setText("");
                currentScore.setText("0/0");
                playAgain.setVisibility(View.INVISIBLE);
                timer();
            }
        });
    }

    private void letsStartTheGame() {

        firstNumber = rand.nextInt(50) + 1;
        secondNumber = rand.nextInt(50) + 1;

        result = firstNumber + secondNumber;
        correctPosition = rand.nextInt(4);
        currentQuestion.setText(Integer.toString(firstNumber) + "+" + Integer.toString(secondNumber));
        listOfQuestions.clear();
        for(int i=0;i<4;i++){

            if(i==correctPosition){
                listOfQuestions.add(result);
            }else {
                listOfQuestions.add(rand.nextInt(101));
            }
        }
        button0.setText(Integer.toString(listOfQuestions.get(0)));
        button1.setText(Integer.toString(listOfQuestions.get(1)));
        button2.setText(Integer.toString(listOfQuestions.get(2)));
        button3.setText(Integer.toString(listOfQuestions.get(3)));
    }

    private void initialise() {
        startGame = (Button)findViewById(R.id.startGame);
        currentQuestion = (TextView)findViewById(R.id.currentQuestion);
        currentScore = (TextView)findViewById(R.id.currentScore);
        timeLeft = (TextView)findViewById(R.id.timeLeft);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        answer = (TextView)findViewById(R.id.result);
        playAgain = (Button)findViewById(R.id.restart);
    }
    public void chooseOption(View view){

        if(view.getTag().toString().equals(Integer.toString(correctPosition))) {
            answer.setText("Correct");
            score++;
        }else{
            answer.setText("WRONG!!");
        }
        questionNumber++;
        currentScore.setText(Integer.toString(score) + "/" + Integer.toString(questionNumber));
        letsStartTheGame();
    }
}