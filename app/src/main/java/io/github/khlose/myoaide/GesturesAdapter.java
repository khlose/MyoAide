package io.github.khlose.myoaide;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.Gesture;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by spinkoh on 8/19/2016.
 */
public class GesturesAdapter extends ArrayAdapter<GestureItem> {

    Context context;
    int layoutResourceId;
    int textViewId;
    ArrayList<GestureItem> gestureList;

    public GesturesAdapter(Context context,int layoutResourceId, int textViewId, ArrayList<GestureItem> gesture) {
        super(context,layoutResourceId, textViewId, gesture);

        Log.d("DEBUG","Constructor");
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.textViewId = textViewId;
        this.gestureList = gesture;
    }

    @Override
    public GestureItem getItem(int position) {
        return this.gestureList.get(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GestureItem gestureItem = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_gesture,parent,false);
       }
        TextView gestureName = (TextView) convertView.findViewById(R.id.gestureName);

        TextView gestureTask = (TextView) convertView.findViewById(R.id.gestureTask);
        ImageView gestureIcon = (ImageView) convertView.findViewById(R.id.gestureIcon);


        gestureName.setText(gestureItem.gesture);
        gestureTask.setText(gestureItem.functionality);


        gestureIcon.setImageResource(gestureItem.iconDrawable);

        return convertView;

    }

    public void AddDummyItem(String gesture, String task){
        GestureDbHelper helper = new GestureDbHelper(getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        String[] projection = {
                GestureContract.GestureEntry.COLUMN_NAME_GESTURE,
        };
        String sortOrder = GestureContract.GestureEntry.COLUMN_NAME_ENTRY_ID + " DESC";
        Cursor C = readableDatabase.query(
                GestureContract.GestureEntry.TABLE_NAME,
                projection,
                GestureContract.GestureEntry.COLUMN_NAME_GESTURE + "=" + " '" + gesture+ "' " ,
                null,
                null,
                null,
                null
        );



        if(C.getCount() == 0 ){
            GestureItem addedGesture = new GestureItem(gesture,task);

            SQLiteDatabase writableDatabase = helper.getWritableDatabase();
            ContentValues mappingValues = new ContentValues();
            //mappingValues.put(GestureContract.GestureEntry.COLUMN_NAME_ENTRY_ID,);
            mappingValues.put(GestureContract.GestureEntry.COLUMN_NAME_GESTURE,addedGesture.gesture);
            mappingValues.put(GestureContract.GestureEntry.COLUMN_NAME_TASK,addedGesture.functionality);
            mappingValues.put(GestureContract.GestureEntry.COLUMN_NAME_ICON,addedGesture.iconDrawable);
            long newRowId = writableDatabase.insert(GestureContract.GestureEntry.TABLE_NAME,null,mappingValues);

            this.add(addedGesture);
            this.notifyDataSetChanged();
            Log.d("Debugged Gesture: ", C.toString());
        }
        else{
            C.moveToFirst();
            String gestureName = C.getString(C.getColumnIndexOrThrow(GestureContract.GestureEntry.COLUMN_NAME_GESTURE));
            Log.d("Debugged Gesture:  ",gestureName);
        }


    }


}
