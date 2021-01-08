package com.example.sudoku;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MakeActivity extends AppCompatActivity {
    int lvl = 0;
    private SudokuView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_make);
        mGridView = findViewById(R.id.SudokuViewMake);
    }


    public void save(View view){
        Spinner spinner = findViewById(R.id.spinner);
        String spin = spinner.getSelectedItem().toString();
        if(spin.equals(getString(R.string.easy))){
            lvl = 1;
        }else if(spin.equals(getString(R.string.medium))){
            lvl = 2;
        }else{
            lvl = 3;
        }
        int[][] made = mGridView.makeBoard();
        String data = "";
        for(int i = 0; i < made.length;i++){
            for(int j = 0; j < made.length; j++){
                data += Integer.toString(made[i][j]);
            }
        }
        String myNewBoard = lvl + "-" + data;
        writeToFile(myNewBoard);
        Toast.makeText(this, getString(R.string.saved) , Toast.LENGTH_LONG).show();
        Log.i("Spinner", spin);
    }

    public void writeToFile(String data){
        File dir = getFilesDir();
        File file = new File(dir, "myBoards.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file, true));
            writer.println(data);

        } catch (Exception e){
            Log.d("WRITER", e.toString());
        } finally {
            writer.close();
        }
    }


    public void numberClickMake(View view){
        int num = -1;
        switch (view.getId()) {
            case R.id.btn1Make:
                num = 1;
                break;
            case R.id.btn2Make:
                num = 2;
                break;
            case R.id.btn3Make:
                num = 3;
                break;
            case R.id.btn4Make:
                num = 4;
                break;
            case R.id.btn5Make:
                num = 5;
                break;
            case R.id.btn6Make:
                num = 6;
                break;
            case R.id.btn7Make:
                num = 7;
                break;
            case R.id.btn8Make:
                num = 8;
                break;
            case R.id.btn9Make:
                num = 9;
                break;
        }

        mGridView.onNumberClick(num, 1);
    }

    public void back2(View view){
        Intent i = new Intent();
        i.putExtra("lang_change", 1);
        setResult(RESULT_OK, i);
        finish();
    }


    public void writeToFile(String[] numbers, int level){
        int file;
        if(level == 1){
            file = R.raw.easy;
        }else if(level == 2){
            file = R.raw.medium;
        }else{
            file = R.raw.hard;
        }

    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void loadLocale(){
        String language = getDefaultSharedPreferences(this).getString("languagePref", "no");
        setLocale(language);
    }
}
