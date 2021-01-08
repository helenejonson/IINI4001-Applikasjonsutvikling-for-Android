package com.example.myfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends Activity{
    Person p;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        p = (Person) getIntent().getSerializableExtra("Personen");
        TextView n = findViewById(R.id.editName);
        TextView b = findViewById(R.id.editBirth);
        n.setText(p.getName());
        b.setText(Integer.toString(p.getBirth()));

    }

    public void save2(View view){
        TextView n = findViewById(R.id.editName);
        TextView b = findViewById(R.id.editBirth);
        String name = n.getText().toString();
        int birth = Integer.parseInt(b.getText().toString());
        p.setName(name);
        p.setBirth(birth);
        Intent i = new Intent();
        i.putExtra("PersonEdit", p );
        setResult(RESULT_OK, i);
        EditActivity.this.finish();

    }

    public void back3(View view){
        EditActivity.this.finish();
    }

}
