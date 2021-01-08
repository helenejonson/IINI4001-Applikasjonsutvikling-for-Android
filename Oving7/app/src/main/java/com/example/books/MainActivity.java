package com.example.books;

import androidx.appcompat.app.AppCompatActivity;
import static android.preference.PreferenceManager.*;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager db;
    private String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = findViewById(R.id.list);
        String backgroundColor = getDefaultSharedPreferences(this).getString("backgroundColorPref", "#ffffff");
        view.setBackgroundColor(Color.parseColor(backgroundColor));
        try {
            db = new DatabaseManager(this);
            db.clean();
            long id = db.insert("Bud Kurniawan","Android Application Development: A Beginners Tutorioal");
            id = db.insert("Mildrid Ljosland", "Programmering i C++");
            id = db.insert("Else Lervik", "Programmering i C++");
            id = db.insert("Mildrid Ljosland", "Algoritmer og datastrukturer");
            id = db.insert("Helge Hafting", "Algoritmer og datastrukturer");
            //createFile();
            readFromFile();
            ArrayList<String> res = db.getAllBooks();
            //   ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
            //   ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
            //   ArrayList<String> res = db.getAllBooksAndAuthors();
            showResults(res);
        }
        catch (Exception e) {
            String tekst = e.getMessage();
            TextView t = findViewById(R.id.list);
            t.setText(tekst);
        }
    }

    //Filen lages og data legges i den. Gjøres bare en gang 
    /*
    public void createFile(){
        File file2 = new File(getFilesDir(), "myBooks.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file2);

            writer.println("Harry Potter og de vises stein:J.K Rowling");
            writer.println("Percy Jackson og lyntyven:Rick Riordan");
            writer.println("Kane Krønikerne:Rick Riordan");
            writer.println("Hobbiten:J.R.R Tolkien");
        } catch (Exception e){
            Log.d(TAG, e.toString());
        } finally {
            writer.close();
        }
    }
     */

    public void readFromFile(){
        File dir = getFilesDir();
        File file = new File(dir, "myBooks.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        ArrayList<String> fromFile = new ArrayList<>();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                fromFile.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (Exception e) {
                Log.d(TAG, "Error while closing bufferreader and filereader.");
            }
        }
        for(int i = 0; i < fromFile.size(); i++){
            String line = fromFile.get(i);
            String[] values = line.split(":");
            long id = db.insert(values[1],values[0]);
        }
    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n\n");
        }
        TextView t = (TextView)findViewById(R.id.list);
        t.setText(res);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.color_settings){
            Intent intent = new Intent("com.example.books.SettingsActivity");
            startActivityForResult(intent, 1);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            TextView view = findViewById(R.id.list);
            String backgroundColor = getDefaultSharedPreferences(this).getString("backgroundColorPref", "#ffffff");
            view.setBackgroundColor(Color.parseColor(backgroundColor));
        }
    }

    public void authors(View view){
        ArrayList<String> res = db.getAllAuthors();
        showResults(res);
    }

    public void names(View view){
        ArrayList<String> res = db.getAllBooks();
        showResults(res);
    }
}