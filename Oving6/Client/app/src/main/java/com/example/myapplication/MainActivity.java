package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Server().start();


    }

    public void sum(View view){

        TextView n1 = findViewById(R.id.number1);
        TextView n2 = findViewById(R.id.number2);
        String nr1 = n1.getText().toString();
        String nr2 = n2.getText().toString();
        String data = nr1 + ":" + nr2;
        new Client(this, data).start();
    }

    public void results(String res){
        TextView result = findViewById(R.id.result);
        result.setText(res);
    }
}