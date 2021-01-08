package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int[] read(){
        TextView left = findViewById(R.id.left);
        int l = Integer.parseInt(left.getText().toString());
        TextView right= findViewById(R.id.right);
        int r = Integer.parseInt(right.getText().toString());
        EditText ansv = findViewById(R.id.asvInput);
        int ans = Integer.parseInt(ansv.getText().toString());
        EditText ovre = findViewById(R.id.ovrInput);
        int ovr = Integer.parseInt(ovre.getText().toString());
        return new int[]{l, r,ans, ovr};
    }

    public void adder(View view){
        int[] list = read();
        int res = list[0] + list[1];
        if(res == list[2]){
            Toast.makeText(this, getString(R.string.rikt), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, getString(R.string.feil) + " " + res, Toast.LENGTH_LONG).show();
        }
        regenerate(list[3]);
    }

    public void multi(View view){
        int[] list = read();
        int res = list[0] * list[1];
        if(res == list[2]){
            Toast.makeText(this, getString(R.string.rikt), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, getString(R.string.feil) + " " + res, Toast.LENGTH_LONG).show();
        }
        regenerate(list[3]);
    }

    public void regenerate(int upper){
        Intent intent = new Intent("com.example.mathgame.RandomActivity");
        intent.putExtra("upper", upper);
        startActivityForResult(intent, 1);
    }

    public void reset(View view){
        int[] list = read();
        regenerate(list[3]);
    }

    @Override
    public void onActivityResult(int requestCode,  int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<Integer> tall = data.getIntegerArrayListExtra("number");
            int randomTall = data.getIntExtra("number", 1);
            TextView left = findViewById(R.id.left);
            left.setText(Integer.toString(tall.get(0)));
            TextView right = findViewById(R.id.right);
            right.setText(Integer.toString(tall.get(1)));
        }
    }
}