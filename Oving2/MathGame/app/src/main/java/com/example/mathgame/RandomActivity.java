package com.example.mathgame;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int upperLimit = getIntent().getIntExtra("upper", 1);

        Random r = new Random();
        int h = r.nextInt(upperLimit);
        int v = r.nextInt(upperLimit);
        ArrayList<Integer> n  = new ArrayList<>();
        n.add(v);
        n.add(h);
        //Toast.makeText(this, n, Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra("number", n );
        setResult(RESULT_OK, intent);
        finish();
    }
}
