package com.example.random;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        int upperLimit = 100;
        Intent intent = new Intent("com.example.random.RandomActivity");
        intent.putExtra("upperLimit", upperLimit);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int randomTall = data.getIntExtra("number", 1);
            TextView textView = findViewById(R.id.number);
            textView.setText(Integer.toString(randomTall));
        }
    }
}