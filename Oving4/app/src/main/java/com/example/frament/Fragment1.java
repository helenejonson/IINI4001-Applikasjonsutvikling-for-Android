package com.example.frament;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment1 extends ListFragment {
    private OnFragmentInteractionListener mListener;
    private int clicked = -1;
    private String[] list;
    private String[] text;

    public Fragment1() {
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int position ,String tekst);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res= getResources();
        list = res.getStringArray(R.array.Planeter);
        text = res.getStringArray(R.array.PlanetFakta);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mListener.onFragmentInteraction(position, text[position]);
        clicked = position;
    }



}
