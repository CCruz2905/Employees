package com.carlos.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SelectEmployee extends AppCompatActivity {

    FeedReaderDbHelper dbHelper;

    protected EditText user;
    protected EditText userEditable;
    protected EditText firstName;
    protected EditText lastName;
    protected EditText lastLastName;
    protected EditText phone;
    protected EditText hireDate;

    protected Button buttonEnable;
    protected Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_employee);

        dbHelper = new FeedReaderDbHelper(getApplicationContext());

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        user = findViewById(R.id.editTextUser);
        userEditable = findViewById(R.id.editViewUserEditable);
        firstName = findViewById(R.id.textViewName);
        lastName = findViewById(R.id.textViewLastName);
        lastLastName = findViewById(R.id.textViewLastLastName);
        phone = findViewById(R.id.textViewPhone);
        hireDate = findViewById(R.id.textViewHireDate);

        buttonEnable = findViewById(R.id.buttonEnable);
        buttonUpdate = findViewById(R.id.buttonUpdate);

//        buttonUpdate.setVisibility(View.GONE);

        disableEnableFields(false);
    }

    public void selectEmployee(View v) {
        cleanFields();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_USER,
                FeedReaderContract.FeedEntry.COLUMN_NAME,
                FeedReaderContract.FeedEntry.COLUMN_LAST_NAME,
                FeedReaderContract.FeedEntry.COLUMN_LAST_LAST_NAME,
                FeedReaderContract.FeedEntry.COLUMN_PHONE,
                FeedReaderContract.FeedEntry.COLUMN_HIRE_DATE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_USER + " = ?";
        String[] selectionArgs = { user.getText().toString() };

        try {
            Cursor cursor = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                    projection,                                // The array of columns to return (pass null to get all)
                    selection,                                 // The columns for the WHERE clause
                    selectionArgs,                             // The values for the WHERE clause
                    null,                             // don't group the rows
                    null,                              // don't filter by row groups
                    null                                 // The sort order
            );
            cursor.moveToFirst();

            userEditable.setText(cursor.getString(1));
            firstName.setText(cursor.getString(2));
            lastName.setText(cursor.getString(3));
            lastLastName.setText(cursor.getString(4));
            phone.setText(cursor.getString(5));
            hireDate.setText(cursor.getString(6));

            cursor.close();

        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(),  "Empleado con usuario: " + user.getText().toString() + " no existe", Toast.LENGTH_LONG);
            toast.show();

            user.setText("");
        }
    }

    public void enableFields(View v) {
        disableEnableFields(true);

        buttonEnable.setVisibility(View.GONE);
        buttonUpdate.setVisibility(View.VISIBLE);
    }

    public void disableFields() {
        disableEnableFields(false);

        buttonEnable.setVisibility(View.VISIBLE);
        buttonUpdate.setVisibility(View.GONE);
    }

    public void updateEmployee(View v) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // New value for one column
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.COLUMN_USER, user.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, firstName.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME, lastName.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_LAST_LAST_NAME, lastLastName.getText().toString());
            values.put(FeedReaderContract.FeedEntry.COLUMN_PHONE, phone.getText().toString());

            // Which row to update, based on the title
            String selection = FeedReaderContract.FeedEntry.COLUMN_USER + " LIKE ?";
            String[] selectionArgs = { user.getText().toString() };

            int count = db.update(
                    FeedReaderContract.FeedEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            Toast toast = Toast.makeText(getApplicationContext(), "Empleado actualizado", Toast.LENGTH_LONG);
            toast.show();

            cleanFields();

        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Empleado no se pudo actualizar", Toast.LENGTH_LONG);
            toast.show();
        }

        disableFields();
        user.setText("");
    }

    public void deleteEmployee(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // Define 'where' part of query.
            String selection = FeedReaderContract.FeedEntry.COLUMN_USER + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { user.getText().toString() };
            // Issue SQL statement.
            int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);

            Toast toast = Toast.makeText(getApplicationContext(), "Empleado dado de baja", Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG);
            toast.show();
        }

        user.setText("");
        cleanFields();
    }

    private void cleanFields() {
        userEditable.setText("");
        firstName.setText("");
        lastName.setText("");
        lastLastName.setText("");
        phone.setText("");
        hireDate.setText("");
    }

    private void disableEnableFields(boolean flag) {
        userEditable.setEnabled(false);
        firstName.setEnabled(flag);
        lastName.setEnabled(flag);
        lastLastName.setEnabled(flag);
        phone.setEnabled(flag);
        hireDate.setEnabled(false);
    }
}