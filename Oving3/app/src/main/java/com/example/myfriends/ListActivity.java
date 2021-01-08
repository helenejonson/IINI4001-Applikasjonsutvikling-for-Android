package com.example.myfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends Activity {
    ArrayList<Person> p = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    int pos;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        p = (ArrayList<Person>) getIntent().getSerializableExtra("Friends");

        for(int i = 0; i < p.size(); i++){
            names.add(p.get(i).toString());
        }
        initierList(names);
    }


    private void initierList(ArrayList<String> friend) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, friend);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View valgt, int posisjon, long id) {
                Person valg = p.get(posisjon);
                pos = posisjon;
                Intent i = new Intent("com.example.myfriends.EditActivity");
                i.putExtra("Personen", valg);
                startActivityForResult(i, 1);
            }
        });
    }


    public void onActivityResult(int requestCode,  int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Person ny = (Person) data.getExtras().getSerializable("PersonEdit");
            p.get(pos).setName(ny.getName());
            p.get(pos).setBirth(ny.getBirth());
            Intent i = new Intent();
            i.putExtra("PersonListe", p );
            setResult(RESULT_OK, i);
            ListActivity.this.finish();

        }
    }

    public void back2(View view){
        Intent i = new Intent();
        i.putExtra("PersonListe", p );
        setResult(RESULT_OK, i);
        ListActivity.this.finish();
    }

}
