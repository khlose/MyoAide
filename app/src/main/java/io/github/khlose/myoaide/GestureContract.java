package io.github.khlose.myoaide;

import android.provider.BaseColumns;

/**
 * Created by spinkoh on 8/22/2016.
 */
public final class GestureContract {
    public GestureContract(){}

    public static abstract class GestureEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_ICON = "icon";
        public static final String COLUMN_NAME_GESTURE = "gesture";
        public static final String COLUMN_NAME_TASK = "task";

    }

}
