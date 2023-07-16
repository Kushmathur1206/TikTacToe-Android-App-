package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // 0 - X
    // 1 - O
    // 2 - NULL
    boolean gameActive = true;
    int activePlayer = 0;
    int[] GS = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] WP = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if ( GS[tappedImage] == 2){
            GS[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else{
                img.setImageResource(R.drawable.circle);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(200);
        }
        else{
            CharSequence text = "Invalid Input";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
            toast.show();
        }
        int sum = Arrays.stream(GS).sum();
        if(sum == 4){
            TextView status = findViewById(R.id.status);
            status.setText("It's a draw");
        }
        for(int[] winPosition: WP) {
            if (GS[winPosition[0]] == GS[winPosition[1]] &&
                    GS[winPosition[1]] == GS[winPosition[2]] &&
                    GS[winPosition[0]] != 2) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (GS[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i<GS.length; i++){
            GS[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView15)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView12)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView13)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView14)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}