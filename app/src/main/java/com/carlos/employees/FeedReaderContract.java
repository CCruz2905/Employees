package com.carlos.employees;

import android.provider.BaseColumns;

/**
 * This class helps organize data and instructions
 *
 * @author Carlos Cruz
 * @version: 1.0
 */
public class FeedReaderContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents
     *  Base Columns give us and primary key called _ID
     * */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "employees";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_LAST_LAST_NAME = "last_last_name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_HIRE_DATE = "hire_date";
    }
}