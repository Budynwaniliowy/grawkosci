package com.example.grawkoci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView[] rzuty;
    private TextView wynikRzutu, liczbaRzutow, wynikGry;
    private Button rzutKoscmiButton, resetujButton;
    private Random random;
    private int licznikRzutow = 0;
    private int sumaWynikowGry = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rzuty = new ImageView[]{
                findViewById(R.id.rzut1),
                findViewById(R.id.rzut2),
                findViewById(R.id.rzut3),
                findViewById(R.id.rzut4),
                findViewById(R.id.rzut5)
        };

        wynikRzutu = findViewById(R.id.wyniki);
        liczbaRzutow = findViewById(R.id.rzuty);
        wynikGry = findViewById(R.id.wynikgry);

        rzutKoscmiButton = findViewById(R.id.rzutkoscmi);
        resetujButton = findViewById(R.id.resetuj);

        random = new Random();

        rzutKoscmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        resetujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        int[] rzutyWartosci = new int[5];
        int[] wystapienia = new int[6];
        int sumaRzutu = 0;

        for (int i = 0; i < 5; i++) {
            rzutyWartosci[i] = random.nextInt(6) + 1;
            wystapienia[rzutyWartosci[i] - 1]++;
        }

        for (int i = 0; i < 6; i++) {
            if (wystapienia[i] >= 2) {
                sumaRzutu += (i + 1) * wystapienia[i];
            }
        }

        displayDiceResults(rzutyWartosci);

        wynikRzutu.setText("Wynik tego losowania: " + sumaRzutu);

        updateScore(sumaRzutu);
        updateRollCount();
    }

    private void resetGame() {
        for (ImageView rzut : rzuty) {
            rzut.setImageResource(R.drawable.dicepust);
        }

        wynikRzutu.setText("Wynik tego losowania: 0");
        wynikGry.setText("Wynik gry: 0");
        liczbaRzutow.setText("Liczba rzutów: 0");

        sumaWynikowGry = 0;
        licznikRzutow = 0;
    }

    private void updateScore(int nowyWynik) {
        sumaWynikowGry += nowyWynik;
        wynikGry.setText("Wynik gry: " + sumaWynikowGry);
    }

    private void updateRollCount() {
        licznikRzutow++;
        liczbaRzutow.setText("Liczba rzutów: " + licznikRzutow);
    }

    private void displayDiceResults(int[] wynikiRzutu) {
        for (int i = 0; i < 5; i++) {
            int diceValue = wynikiRzutu[i];
            int resID = getResources().getIdentifier("dice" + diceValue, "drawable", getPackageName());
            rzuty[i].setImageResource(resID);
        }
    }
}