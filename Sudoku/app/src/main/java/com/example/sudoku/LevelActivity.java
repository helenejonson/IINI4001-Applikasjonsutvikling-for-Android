package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class LevelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_level);
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

    public void easy(View view){
        Intent intent = new Intent("com.example.sudoku.PlayActivity");
        intent.putExtra("level", 1);
        startActivityForResult(intent, 1);
    }

    public void medium(View view){
        Intent intent = new Intent("com.example.sudoku.PlayActivity");
        intent.putExtra("level", 2);
        startActivityForResult(intent, 1);
    }

    public void hard(View view){
        Intent intent = new Intent("com.example.sudoku.PlayActivity");
        intent.putExtra("level", 3);
        startActivity(intent);
    }

    public void back(View view){
        Intent i = new Intent();
        i.putExtra("lang_change", 1);
        setResult(RESULT_OK, i);
        finish();
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
}
