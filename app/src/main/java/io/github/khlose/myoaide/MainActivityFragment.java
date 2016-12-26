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
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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


    GestureItemAdapter gestureItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);




        GestureDbHelper helper = new GestureDbHelper(getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor gestureCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);

        gestureItemAdapter = new GestureItemAdapter(getContext(),gestureCursor);

        final ListView listView = (ListView)rootView.findViewById(R.id.listview_gesture);

        listView.setAdapter(gestureItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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


        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(listView.getCheckedItemCount() + " gestures selected");

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_cab,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_delete:
                        Log.d("Debug Appearance","DELETE CLICKED");
                        deleteSelectedItems();
                        gestureItemAdapter.notifyDataSetChanged();

                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
            private void deleteSelectedItems(){
                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                GestureDbHelper helper = new GestureDbHelper(getContext());
                SQLiteDatabase writeableDatabase = helper.getWritableDatabase();
                //Cursor gestureCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);
                for(int i = 0; i<checkedItems.size();i++){
                    if(checkedItems.valueAt(i) == true){

                        Cursor cursor = ((GestureItemAdapter)listView.getAdapter()).getCursor();
                        cursor.moveToPosition(checkedItems.keyAt(i));
                        String gestureSelected = cursor.getString(cursor.getColumnIndex(GestureContract.GestureEntry.COLUMN_NAME_GESTURE));

                        writeableDatabase.delete(GestureContract.GestureEntry.TABLE_NAME,"gesture=?",new String[]{ gestureSelected});




                        //String gestureDelete = listView.getItemAtPosition(checkedItems.keyAt(i));
                    }
                    //writeableDatabase.delete(GestureContract.GestureEntry.TABLE_NAME,"gesture=?",new String{ listView.getItemAtPosition(checkedItems.k)})

                }

                SQLiteDatabase readableDatabase = helper.getReadableDatabase();
                Cursor updatedCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);

                gestureItemAdapter.changeCursor(updatedCursor);
                gestureItemAdapter.notifyDataSetChanged();
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

    @Override
    public void onResume() {
        super.onResume();
        GestureDbHelper helper = new GestureDbHelper(getContext());
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor updatedCursor = readableDatabase.rawQuery("SELECT * FROM " + GestureContract.GestureEntry.TABLE_NAME,null);

        gestureItemAdapter.changeCursor(updatedCursor);

    }
}
