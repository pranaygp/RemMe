package com.birdle.remme;

import android.provider.BaseColumns;

/**
 * Created by pranaygp on 14/08/15.
 */
public final class remsContract {
    public remsContract(){}

    public static abstract class remSchema implements BaseColumns {
        public static final String TABLE_NAME = "rems";
        public static final String COLUMN_NAME_REM_DATA= "data";
        public static final String[] COLUMN_NAMES= {TABLE_NAME, COLUMN_NAME_REM_DATA};

    }
}
