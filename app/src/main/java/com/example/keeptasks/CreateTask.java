package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.example.keeptasks.DataBaseHelper;
import com.example.keeptasks.MainActivity;
import com.example.keeptasks.TaskObj;
import com.example.keeptasks.constants;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CreateTask extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button btnDate, btnTime;
    private Date dateObj = new Date();
    private Time timeObj = new Time();
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        // Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); // return to Home Page
        // On Screen Objects
        Button btndn = (Button) findViewById(R.id.btnalltask); // The Done button
        Button btncln = (Button) findViewById(R.id.btncancel); // The Cancel button
        EditText txtName = (EditText) findViewById(R.id.taskfiled);// The Title Field
        Switch urgentS = (Switch) findViewById(R.id.switchurgent);// The Urgent Switch
        EditText txtNote = (EditText) findViewById(R.id.notefiled);// The Note Field
        Switch dayBeforeS = (Switch) findViewById(R.id.switchremind);// The Day Before Switch
        btnDate = (Button) findViewById(R.id.dateinput);// Date picker
        btnTime = (Button) findViewById(R.id.timeinput);// Time picker
        // set current day
        btnDate.setText(dateObj.getTodayDate());
        // set current time
        btnTime.setText(timeObj.getCurrentTime());
        // set hints
        txtName.setHintTextColor(Color.WHITE);
        txtName.setHint("Task Title");
        txtNote.setHintTextColor(Color.WHITE);
        txtNote.setHint("Notes");
        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() { // Done Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Finish button");
                String Name = txtName.getText().toString();// Get the Title
                String Note = txtNote.getText().toString();// Get the Note
                String Date = btnDate.getText().toString();// Get the Date (Might change this to someother type late)
                Boolean DateBefroe = dayBeforeS.isChecked();// Get Day Before status
                Boolean urgent = urgentS.isChecked();// Get Urgent status
                String time = btnTime.getText().toString();
                if (Name.matches("")) {// if Title is empty
                    Toast.makeText(getApplicationContext(), constants.name_Empty_Message, Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: add it as an alarm and a notfication
                    // Added to DataBase
                    DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                    TaskObj task = new TaskObj(0, Name, Date, urgent, DateBefroe, Note,time);
                    boolean success = dbHelper.addOne(task, DataBaseHelper.table_name);
                    // TODO: add it as an alarm and a notfication
                    Toast.makeText(getApplicationContext(), constants.task_Added_Message + success, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        };
        // Listeners
        android.view.View.OnClickListener cancellistener = new View.OnClickListener() { // Done Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the cancel button");
                startActivity(intent);
                finish();
            }
        };

        android.view.View.OnClickListener popuplistener = new View.OnClickListener() { // Date Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the calender button");
                initDatePicker();
                datePickerDialog.show();
            }
        };

        android.view.View.OnClickListener timelistener = new View.OnClickListener() { // Time Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the clock button");
                initTimePicker();
                timePickerDialog.show();
            }
        };
        // set Listener
        btndn.setOnClickListener(donelistener);
        btncln.setOnClickListener(cancellistener);
        btnDate.setOnClickListener(popuplistener);
        btnTime.setOnClickListener(timelistener);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                btnDate.setText(dateObj.makeDateString(day, month + 1, year));
            }
        };
        int year = this.cal.get(Calendar.YEAR);
        int month = this.cal.get(Calendar.MONTH); // it starts at zero
        int day = this.cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timePickerlistener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minutes) {
                btnTime.setText((timeObj.makeTimeString(hour, minutes)));
            }
        };
        int hour = this.cal.get(Calendar.HOUR);
        int minutes = this.cal.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, timePickerlistener, hour, minutes, true);
    }
}