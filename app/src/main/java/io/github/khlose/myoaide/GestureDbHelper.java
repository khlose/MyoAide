package io.github.khlose.myoaide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by spinkoh on 8/22/2016.
 */
public class GestureDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = "TEXT";
    private static final String INT_TYPE = "INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GestureContract.GestureEntry.TABLE_NAME + " (" +
                    GestureContract.GestureEntry._ID + " INTEGER PRIMARY KEY," +
                    GestureContract.GestureEntry.COLUMN_NAME_ENTRY_ID + " " + TEXT_TYPE + COMMA_SEP +
                    GestureContract.GestureEntry.COLUMN_NAME_ICON + " " + INT_TYPE + COMMA_SEP +
                    GestureContract.GestureEntry.COLUMN_NAME_GESTURE + " " + TEXT_TYPE + COMMA_SEP +
                    GestureContract.GestureEntry.COLUMN_NAME_TASK + " " + TEXT_TYPE
                    + " )"
            ;
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + GestureContract.GestureEntry.TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedGesture.db";

    public GestureDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newViersion){
        onUpgrade(db,oldVersion,newViersion);
    }
}
