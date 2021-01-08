package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //createFile(); //Kjørtes bare en gang når filen ble laget
        //resetFile(); //Tømmer filen av selvlagde brett

    }

    public void resetFile(){
        File dir = getFilesDir();
        File file = new File(dir, "myBoards.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println("1-902415000005060000379999961210396005406000002003080190649031057500600004807509000");

        } catch (Exception e){
            Log.d("WRITER", e.toString());
        } finally {
            writer.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.setting){
            Intent intent = new Intent("com.example.sudoku.SettingsActivity");
            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    public void play(View view){
        Intent intent = new Intent("com.example.sudoku.LevelActivity");
        startActivityForResult(intent, 1);
    }

    public void make(View view){
        Intent intent = new Intent("com.example.sudoku.MakeActivity");
        startActivityForResult(intent, 1);
    }

    public void manual(View view){
        Intent intent = new Intent("com.example.sudoku.ManualActivity");
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String language = getDefaultSharedPreferences(this).getString("languagePref", "no");
            Log.i("Lang", language);
            if(language.equals("no")) {
                setLocale(language);
                recreate();
            }if(language.equals("en")){
                setLocale(language);
                recreate();
            }
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

    public void createFile(){
        File file = new File(getFilesDir(), "myBoards.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println("1-902415000005060000379999961210396005406000002003080190649031057500600004807509000");

        } catch (Exception e){
            Log.d("PRINT", e.toString());
        } finally {
            writer.close();
        }
    }
}