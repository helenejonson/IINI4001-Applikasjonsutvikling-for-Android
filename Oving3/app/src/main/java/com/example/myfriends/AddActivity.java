package com.example.myfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends Activity {
    ArrayList<Person> list  = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void back(View view){
        Intent i = new Intent();
        i.putExtra("Person", list );
        setResult(RESULT_OK, i);
        AddActivity.this.finish();
    }

    public void save(View view){
        TextView n = findViewById(R.id.addName);
        TextView b = findViewById(R.id.addBirth);
        String name = n.getText().toString();
        int birth = Integer.parseInt(b.getText().toString());
        if(name.equals("")){
            Toast.makeText(this, "Fyll ut all informasjon", Toast.LENGTH_LONG).show();
        }else{
            Person p = new Person(name, birth);
            list.add(p);
            n.setText("");
            b.setText("");
            Toast.makeText(this, "Lagret", Toast.LENGTH_LONG).show();
        }

    }
}
