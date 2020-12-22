package com.carlos.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import java.text.DateFormat;
import java.util.Date;

/**
 * This class helps insert values into SQLite.
 * @author Carlos Cruz
 * @version: 1.0
 */
public class InsertEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_employee);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
    }

    /**
     * Helps to insert values into employees table
     */
    public void insertEmployee() {
        String strDate = DateFormat.getDateInstance().format(new Date());

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_HIRE_DATE, strDate);
    }
}