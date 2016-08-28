package io.github.khlose.myoaide;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by spinkoh on 8/26/2016.
 */
public class GestureItemAdapter extends CursorAdapter{
    public GestureItemAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_gesture,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView gestureTextView = (TextView) view.findViewById(R.id.gestureName);
        TextView taskTextView = (TextView) view.findViewById(R.id.gestureTask);
        ImageView logoImageView = (ImageView) view.findViewById(R.id.gestureIcon);

        String gesture = cursor.getString(cursor.getColumnIndexOrThrow(GestureContract.GestureEntry.COLUMN_NAME_GESTURE));
        String task = cursor.getString(cursor.getColumnIndexOrThrow(GestureContract.GestureEntry.COLUMN_NAME_TASK));

        int icon = cursor.getInt(cursor.getColumnIndexOrThrow(GestureContract.GestureEntry.COLUMN_NAME_ICON));

        gestureTextView.setText(gesture);
        taskTextView.setText(task);
        logoImageView.setImageResource(icon);
    }


}
