package com.example.frament;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    private String[] list;
    private String[] text;

    public Fragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res= getResources();
        list = res.getStringArray(R.array.Planeter);
        text = res.getStringArray(R.array.PlanetFakta);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }


    public void endreTekst(int id, String tekst) {
        TextView t = (getView().findViewById(R.id.beskrivelse));
        ImageView imageView = getView().findViewById(R.id.image);

        t.setText(tekst);
        imageView.setImageResource(id);
    }



}
