package com.example.myfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Person> Persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showList(View view){
        ArrayList<Person> s = new ArrayList<>();
        Person o = new Person("Helene", 12222);
        s.add(o);
        Intent i = new Intent("com.example.myfriends.ListActivity");
        i.putExtra("Friends", Persons);
        startActivityForResult(i,2);
    }

    public void addFriends(View view){
        Intent i = new Intent("com.example.myfriends.AddActivity");
        startActivityForResult(i, 1);

    }

    @Override
    public void onActivityResult(int requestCode,  int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<Person> p = (ArrayList<Person>) data.getExtras().getSerializable("Person");
            for(int i = 0; i < p.size(); i++){
                Persons.add(p.get(i));
            }
        }if(requestCode == 2 && resultCode == RESULT_OK){
            ArrayList<Person> p = (ArrayList<Person>) data.getExtras().getSerializable("PersonListe");
            Persons = p;
        }
    }

}

/*
if(list.size() != 0){
                for(int i = 0; i < list.size(); i++){
                    String [] personString = list.get(i).split("-");
                    Person p = new Person(personString[0], Integer.parseInt(personString[1]));
                    Persons.add(p);
                }
                Toast.makeText(this, Persons.get(0).toString(), Toast.LENGTH_LONG).show();
            }
 */