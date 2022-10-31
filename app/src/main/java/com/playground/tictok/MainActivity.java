package com.playground.tictok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int playerActive = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    public static int count = 0;

    boolean gameActive = true;

    int[][] winningPosition = {
            {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
    };

    public void OnTapImage(View view) {
        ImageView imageview = (ImageView)view;

        int imageTapped = Integer.parseInt(imageview.getTag().toString());

        if (gameState[imageTapped] == 2 && gameActive) {
            imageview.setTranslationY(-1000);
            gameState[imageTapped] = playerActive;
            count++;

            if(count == 9){
                gameActive = false;
            }

            if (playerActive == 0) {
                imageview.setImageResource(R.drawable.game_cross);
                playerActive = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn");
            } else {
                imageview.setImageResource(R.drawable.game_o);
                imageview.setMaxHeight(10);
                playerActive = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn");

            }
            imageview.animate().translationYBy(1000).setDuration(200);

            int draw = 1;
            for(int[] winningPos : winningPosition){
                if(gameState[winningPos[0]] == gameState[winningPos[1]]
                        && gameState[winningPos[1]] == gameState[winningPos[2]]
                        && gameState[winningPos[0]] != 2){
                    draw = 0;
                    String winner;
                    if(gameState[winningPos[0]] == 0){
                        winner = "X has won";
                    }else{
                        winner = "O has won";
                    }
                    TextView status = findViewById(R.id.status);
                    status.setText(winner);

                    Button playAgainBtn = findViewById(R.id.playAgain_btn);
                    playAgainBtn.setVisibility(View.VISIBLE);
                }
            }

            // Draw condition
            if(count == 9 && draw == 1){
                TextView status = findViewById(R.id.status);
                status.setText("Match Draw");

                Button playAgainBtn = findViewById(R.id.playAgain_btn);
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view){
        Button playAgainBtn = findViewById(R.id.playAgain_btn);
        playAgainBtn.setVisibility(View.INVISIBLE);

        TextView status = findViewById(R.id.status);
        status.setText("O's turn");

        gameActive = true;
        playerActive = 0;
        count = 0;


        //Setting gameState to empty
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.ImageView0)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView1)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView2)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView3)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView4)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView5)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView6)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView7)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.ImageView8)).setImageDrawable(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}