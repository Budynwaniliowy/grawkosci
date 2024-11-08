package com.example.grawkosci2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView rzut1, rzut2, rzut3, rzut4, rzut5;
    private TextView wyniki, rzuty, wynikgry;
    private Button rzutKoscmiButton, resetujButton;
    private Random random;
    private int liczbaRzutow = 0;
    private int wynikGry = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rzut1 = findViewById(R.id.rzut1);
        rzut2 = findViewById(R.id.rzut2);
        rzut3 = findViewById(R.id.rzut3);
        rzut4 = findViewById(R.id.rzut4);
        rzut5 = findViewById(R.id.rzut5);
        wyniki = findViewById(R.id.wyniki);
        rzuty = findViewById(R.id.rzuty);
        wynikgry = findViewById(R.id.wynikgry);

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
        int[] rzutyKosci = new int[5];
        int sumaRzutu = 0;

        for (int i = 0; i < 5; i++) {
            rzutyKosci[i] = random.nextInt(6) + 1;
            sumaRzutu += rzutyKosci[i];
        }

        rzut1.setText(String.valueOf(rzutyKosci[0]));
        rzut2.setText(String.valueOf(rzutyKosci[1]));
        rzut3.setText(String.valueOf(rzutyKosci[2]));
        rzut4.setText(String.valueOf(rzutyKosci[3]));
        rzut5.setText(String.valueOf(rzutyKosci[4]));

        wyniki.setText("Wynik tego losowania: " + sumaRzutu);

        wynikGry += sumaRzutu;
        liczbaRzutow++;
        wynikgry.setText("Wynik gry: " + wynikGry);
        rzuty.setText("Liczba rzutów: " + liczbaRzutow);
    }

    private void resetGame() {
        rzut1.setText("?");
        rzut2.setText("?");
        rzut3.setText("?");
        rzut4.setText("?");
        rzut5.setText("?");

        wyniki.setText("Wynik tego losowania: 0");
        wynikgry.setText("Wynik gry: 0");
        rzuty.setText("Liczba rzutów: 0");

        wynikGry = 0;
        liczbaRzutow = 0;
    }
}
