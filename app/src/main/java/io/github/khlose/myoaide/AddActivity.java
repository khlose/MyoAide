package io.github.khlose.myoaide;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                save_gesture();
                finish();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return false;
        }

    }
    private void save_gesture(){
        GestureDbHelper helper = new GestureDbHelper(this);
        SQLiteDatabase writeableDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        Spinner gestureSpinner = (Spinner) findViewById(R.id.gestureSpinner);
        String gestureString = gestureSpinner.getSelectedItem().toString();
        Spinner taskSpinner = (Spinner) findViewById(R.id.taskSpinner);
        String taskString = taskSpinner.getSelectedItem().toString();

        GestureItem addedGesture = new GestureItem(gestureString,taskString);


        values.put(GestureContract.GestureEntry.COLUMN_NAME_GESTURE,gestureString);
        values.put(GestureContract.GestureEntry.COLUMN_NAME_TASK,taskString);
        values.put(GestureContract.GestureEntry.COLUMN_NAME_ICON,addedGesture.iconDrawable);
        writeableDatabase.insert(GestureContract.GestureEntry.TABLE_NAME, null, values);
    }




}
