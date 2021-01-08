package com.example.random;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;


public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int upperLimit = getIntent().getIntExtra("upperLimit", 1);

        Random r = new Random();
        int n = r.nextInt(upperLimit);
        //Toast.makeText(this, number, Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra("number", n);
        setResult(RESULT_OK, intent);
        finish();
    }
}