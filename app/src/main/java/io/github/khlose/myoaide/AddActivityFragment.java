package io.github.khlose.myoaide;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddActivityFragment extends Fragment {

    public AddActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        GestureDbHelper helper = new GestureDbHelper(getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor gestureCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);
        ArrayList<String> gestureArray = new ArrayList<String>();
        ArrayList<String> allGestureArray = new ArrayList<String>(){{ add("wave in"); add("double tap"); add("fist"); add("wave out"); add("fingers spread"); }};
        ArrayList<String> taskArray = new ArrayList<String>();
        ArrayList<String> allTaskArray = new ArrayList<String>(){{ add("pick up"); add("hang up"); add("increase volume"); add("decrease volume"); }};

        while(gestureCursor.moveToNext()){
            gestureArray.add(gestureCursor.getString(gestureCursor.getColumnIndex("gesture")));
            taskArray.add(gestureCursor.getString(gestureCursor.getColumnIndex("task")));
        }
        allGestureArray.removeAll(gestureArray);
        allTaskArray.removeAll(taskArray);
        Log.d("GESTURE ARRAY",allGestureArray.toString());
        Log.d("TASK ARRAY",allTaskArray.toString());

        Spinner gestureSpinner = (Spinner) rootView.findViewById(R.id.gestureSpinner);
        ArrayAdapter<String> gestureArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,allGestureArray);
        gestureSpinner.setAdapter(gestureArrayAdapter);

        Spinner taskSpinner = (Spinner) rootView.findViewById(R.id.taskSpinner);
        ArrayAdapter<String> taskArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,allTaskArray);
        taskSpinner.setAdapter(taskArrayAdapter);

        return rootView;
    }


}
