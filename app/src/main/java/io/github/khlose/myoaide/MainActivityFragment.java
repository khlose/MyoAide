package io.github.khlose.myoaide;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    public GesturesAdapter gestureAdapter;
    public ArrayList<GestureItem> gestureArrayList = new ArrayList<GestureItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gestureArrayList.add(new GestureItem("wave in","pickup phone"));
        gestureArrayList.add(new GestureItem("wave out","pickup phone"));

        gestureAdapter = new GesturesAdapter(getActivity(),R.layout.list_item_gesture,R.id.gestureName,gestureArrayList);
        gestureAdapter.notifyDataSetChanged();

        final ListView listView = (ListView)rootView.findViewById(R.id.listview_gesture);

        listView.setAdapter(gestureAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String forecastString = forecastAdapter.getItem(positin);

                /*
                Intent detailIntent = new Intent(getActivity(),AddActivity.class);
                detailIntent.putExtra(Intent.EXTRA_TEXT, forecastStrig);
                startActivity(detailIntent);
               */

            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                gestureAdapter.AddDummyItem("wave in","Pick up call");

            }
        });



        return rootView;
    }



}
