package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class PlayActivity extends AppCompatActivity  {
    private int level;
    private SudokuView mGridView;
    private SudokuGameBoard myGame = new SudokuGameBoard();
    private int [][] board = new int[9][9];
    private ArrayList<String> allBoards = new ArrayList<>();
    private ArrayList<String> readFromFile = new ArrayList<>();




    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_play);
        level = getIntent().getIntExtra("level", -1);
        TextView lvl = findViewById(R.id.levelview);
        mGridView = findViewById(R.id.SudokuView);
        if(level == 1){

            lvl.setText(getString(R.string.easy));
        }else if(level == 2){
            lvl.setText(R.string.medium);
        } else if(level == 3){
            lvl.setText(R.string.hard);
        }
        board = readBoard();
        mGridView.startBoard(board);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.easy) {
            Intent intent = new Intent("com.example.sudoku.PlayActivity");
            intent.putExtra("level", 1);
            startActivity(intent);
            finish();
        }
        if(id == R.id.medium){
            Intent intent = new Intent("com.example.sudoku.PlayActivity");
            intent.putExtra("level", 2);
            startActivity(intent);
            finish();

        }
        if(id == R.id.hard){
            Intent intent = new Intent("com.example.sudoku.PlayActivity");
            intent.putExtra("level", 3);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    public int[][] readBoard(){
        int file;
        if(level == 1){
            file = R.raw.easy;
        }else if(level == 2){
            file = R.raw.medium;
        }else{
            file = R.raw.hard;
        }
        int[][] myBoard = new int[9][9];
        InputStream inn = getResources().openRawResource(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(inn));

        try{
            String line = br.readLine();
            while(line != null){
                allBoards.add(line);
                line = br.readLine();
            }

            ArrayList<String> fileBoards = getFromFile(Integer.toString(level));
            for(int i = 0; i < fileBoards.size(); i++){
                allBoards.add(fileBoards.get(i));
            }

            Random r = new Random();
            int rand = r.nextInt(allBoards.size());
            String thisBoard = allBoards.get(rand);
            String[] num = thisBoard.split("");
            int count = 0;
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    myBoard[i][j] = Integer.parseInt(num[count]);
                    count++;
                }
            }
        }catch (IOException e){
            Log.e("Error", e.toString());
        }
        return myBoard;
    }

    public void readFromFile(){
        File dir = getFilesDir();
        File file = new File(dir, "myBoards.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        ArrayList<String> fromFile = new ArrayList<>();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                readFromFile.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            Log.d("READ", e.toString());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (Exception e) {
                Log.d("READ", "Error while closing bufferreader and filereader.");
            }
        }
    }

    public ArrayList<String> getFromFile(String lvl){
        readFromFile();
        ArrayList<String> myFileBoards = new ArrayList<>();
        for(int i = 0; i < readFromFile.size(); i++){
            String line = readFromFile.get(i);
            String[] spiltLine = line.split("-");
            if(spiltLine[0].equals(lvl)){
                myFileBoards.add(spiltLine[1]);
            }

        }
        return myFileBoards;
    }

    public void numberClick(View view){
        int num = -1;
        switch (view.getId()) {
            case R.id.btn1:
                num = 1;
                break;
            case R.id.btn2:
                num = 2;
                break;
            case R.id.btn3:
                num = 3;
                break;
            case R.id.btn4:
                num = 4;
                break;
            case R.id.btn5:
                num = 5;
                break;
            case R.id.btn6:
                num = 6;
                break;
            case R.id.btn7:
                num = 7;
                break;
            case R.id.btn8:
                num = 8;
                break;
            case R.id.btn9:
                num = 9;
                break;
        }
        CheckBox box = findViewById(R.id.red);
        int col = 1;
        if(box.isChecked()){
            col = 2;
        }
        mGridView.onNumberClick(num, col);
    }

    public void checkBoard(View view){
        int[][] played = mGridView.checkBoard();
        myGame.setFullBoard(played);
        boolean done = myGame.checkBoard();
        TextView res = findViewById(R.id.result);
        if(done){
            res.setText(getString(R.string.right));
            res.setBackgroundColor(Color.GREEN);
        }else{
            res.setText(getString(R.string.wrong));
            res.setBackgroundColor(Color.RED);
        }
    }

    public void remove(View view){
        mGridView.onRemoveClick();
    }

    public void reset(View view){
        mGridView.restart();
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
