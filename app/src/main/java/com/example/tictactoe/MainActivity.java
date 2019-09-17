package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //yellow = 0, red = 1

    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,6,7},{6,4,3},{1,2,5},{0,6,1},{7,4,2},{8,3,5},{0,4,5},{8,4,1}};

    public void dropin(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won
                    gameIsActive = false;
                    String winner =  "Cross";

                    if(gameState[winningPosition[0]] == 0){
                        winner = "Circle";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout Layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    Layout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                        if (counterState == 2){
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!");
                        LinearLayout Layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        Layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout Layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        Layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        for (int i = 1; i <= 9; i++){
            String imageViewName = "imageView" + i;
            int resID = getResources().getIdentifier(imageViewName, "id", getPackageName());
            ImageView img1 = (ImageView)findViewById(resID);
            img1.setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
