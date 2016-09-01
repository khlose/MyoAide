package io.github.khlose.myoaide;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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




        GestureDbHelper helper = new GestureDbHelper(getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();


        List<android.util.Pair<String,String>> stringPair = readableDatabase.getAttachedDbs();
        Log.d("DB debug:",(stringPair.get(1).second));
        Cursor gestureCursor = readableDatabase.rawQuery("SELECT  * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);
        GestureItemAdapter gestureItemAdapter = new GestureItemAdapter(getContext(),gestureCursor);



        /*
        gestureArrayList.add(new GestureItem("wave in","pickup phone"));
        gestureArrayList.add(new GestureItem("wave out","pickup phone"));




        gestureAdapter = new GesturesAdapter(getActivity(),R.layout.list_item_gesture,R.id.gestureName,gestureArrayList);
        gestureAdapter.notifyDataSetChanged();
        */



        final ListView listView = (ListView)rootView.findViewById(R.id.listview_gesture);



        listView.setAdapter(gestureItemAdapter);

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

                //gestureAdapter.AddDummyItem("wave out","Pick up call");

            }
        });



        return rootView;
    }



}
