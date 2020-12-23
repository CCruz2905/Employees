package com.carlos.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * This class helps insert values into SQLite.
 * @author Carlos Cruz
 * @version: 1.0
 */
public class InsertEmployee extends AppCompatActivity {
    // **************************************************
    // Fields
    // **************************************************
    protected EditText user;
    protected EditText firstName;
    protected EditText lastName;
    protected EditText lastLastName;
    protected EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_employee);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        user = findViewById(R.id.editTextUser);
        firstName = findViewById(R.id.textViewName);
        lastName = findViewById(R.id.textViewLastName);
        lastLastName = findViewById(R.id.textViewLastLastName);
        phone = findViewById(R.id.textViewPhone);
    }

    /**
     * Helps to insert values into employees table
     */
    public void insertEmployee(View v) {
        // Gets the data repository in write mode
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String strDate = DateFormat.getDateInstance().format(new Date());

        try {
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.COLUMN_USER, user.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, firstName.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME, lastName.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_LAST_LAST_NAME, lastLastName.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_PHONE, phone.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_HIRE_DATE, strDate);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

            cleanFields();

            Toast toast = Toast.makeText(getApplicationContext(), "Empleado agregado correctamente", Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "El empleado no pudo agregarse", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void cleanFields() {
        user.setText("");
        firstName.setText("");
        lastName.setText("");
        lastLastName.setText("");
        phone.setText("");
    }
}