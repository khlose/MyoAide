package io.github.khlose.myoaide;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.Objects;
import java.util.StringTokenizer;

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
        Cursor gestureCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);

        final GestureItemAdapter gestureItemAdapter = new GestureItemAdapter(getContext(),gestureCursor);

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

                Cursor cursor = ((GestureItemAdapter)listView.getAdapter()).getCursor();
                cursor.moveToPosition(position);
                String gestureSelected = cursor.getString(cursor.getColumnIndex(GestureContract.GestureEntry.COLUMN_NAME_GESTURE));
                String taskSelected = cursor.getString(cursor.getColumnIndex(GestureContract.GestureEntry.COLUMN_NAME_TASK));
                int iconSelected = cursor.getInt(cursor.getColumnIndex(GestureContract.GestureEntry.COLUMN_NAME_TASK));

                Intent addGestureIntent = new Intent(getActivity(),AddActivity.class);
                addGestureIntent.putExtra("isAdding",false);
                addGestureIntent.putExtra("gesture",gestureSelected);
                addGestureIntent.putExtra("task",taskSelected);
                addGestureIntent.putExtra("iconId",iconSelected);

                startActivity(addGestureIntent);


            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent addGestureIntent = new Intent(getActivity(),AddActivity.class);
                addGestureIntent.putExtra("isAdding",true);

                startActivity(addGestureIntent);

            /*

            gestureItemAdapter.addGesture("wave in","sup");
            GestureDbHelper helper = new GestureDbHelper(getContext());
            SQLiteDatabase readableDatabase = helper.getReadableDatabase();
            Cursor updatedCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);

            gestureItemAdapter.changeCursor(updatedCursor);
            */


            }
        });



        return rootView;
    }



}
