package com.example.frament;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {
    int selected = -1;
    private TypedArray pictures;
    private String[] desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources resources = getResources();
        pictures = resources.obtainTypedArray(R.array.pictures);
        desc = resources.getStringArray(R.array.PlanetFakta);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void prev(){
        selected--;
        String text = desc[selected];
        onFragmentInteraction(selected, text);

    }

    public void next(){
        selected++;
        String text = desc[selected];
        onFragmentInteraction(selected, text);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_prev:
                prev();
                return true;
            case R.id.menu_next:
                next();
                return true;
            case android.R.id.home:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int position, String text) {
        selected = position;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment2 fragment = (Fragment2)fm.findFragmentById(R.id.fragment2);
        int id = pictures.peekValue(position).resourceId;
        fragment.endreTekst(id, text);
    }
}
