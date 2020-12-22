package com.carlos.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This class helps listen to the user request,
 * according to selection (insert, select) employee
 * @author Carlos Cruz
 * @version: 1.0
 */
public class MainActivity extends AppCompatActivity {

    // **************************************************
    // Protected methods
    // **************************************************

    /**
     * Initialize Main Activity View
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize our database employees
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
    }

    // **************************************************
    // Private methods
    // **************************************************
    /**
     * OnClick Method, helps redirect to InsertView
     *
     */
    public void insertEmployeeView(View v) {
        Intent intent = new Intent(this, InsertEmployee.class);
        startActivity(intent);
    }
}