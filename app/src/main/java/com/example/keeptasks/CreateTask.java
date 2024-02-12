package com.example.keeptasks;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import java.util.Calendar;

public class CreateTask extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Date dateObj = new Date();
    private Time timeObj = new Time();
    private Calendar cal = Calendar.getInstance();
    private Button btnDate, btnTime;
    private EditText txtName, txtNote;
    private Switch urgentS, dayBeforeS;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int year, month, day, hour, minutes;
    private long alarm_repeate = 300000;
    private TaskObj task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        // Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); // return to Home Page
        // On Screen Objects
        Button btnDone = (Button) findViewById(R.id.btnalltask); // The Done button
        Button btnCancel = (Button) findViewById(R.id.btncancel); // The Cancel button
        txtName = (EditText) findViewById(R.id.taskfiled);// The Title Field
        urgentS = (Switch) findViewById(R.id.switchurgent);// The Urgent Switch
        txtNote = (EditText) findViewById(R.id.notefiled);// The Note Field
        dayBeforeS = (Switch) findViewById(R.id.switchremind);// The Day Before Switch
        btnDate = (Button) findViewById(R.id.dateinput);// Date picker
        btnTime = (Button) findViewById(R.id.timeinput);// Time picker
        // set current day
        btnDate.setText(dateObj.getTodayDate());
        // set current time
        btnTime.setText(timeObj.getCurrentTime());
        // set hints
        txtName.setHintTextColor(Color.WHITE);
        txtName.setHint(constants.titleHint);
        txtNote.setHintTextColor(Color.WHITE);
        txtNote.setHint(constants.noteHint);
        //
        DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
        task = MainActivity.getHeldTask();
        if (task != null) {
            loadTask(task);
            dbHelper.completeTask(task);
            MainActivity.setHeldTask(null);
        }
        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() { // Done Button Listener
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Finish button");
                String name = txtName.getText().toString();// Get the Title
                String note = txtNote.getText().toString();// Get the Note
                String date = btnDate.getText().toString();// Get the Date (Might change this to someother type late)
                String time = btnTime.getText().toString();// Get Time
                Boolean dateBefroe = dayBeforeS.isChecked();// Get Day Before status
                Boolean urgent = urgentS.isChecked();// Get Urgent status
                if (name.matches("")) {// if Title is empty
                    Toast.makeText(getApplicationContext(), constants.nameEmptyMessage, Toast.LENGTH_SHORT).show();
                } else {
                    // Added to DataBase
                    TaskObj task = new TaskObj(0, name, date, urgent, dateBefroe, note, time);
                    boolean success = dbHelper.addOne(task, DataBaseHelper.tableName);

                    // testing alarm
                    AlarmReceiver.setDetailsAlarm("channel 1", name, note);
                    // Toast.makeText(getApplicationContext(), "current time" +
                    // timeObj.timeInMillis() + "future time" + timeObj.futureTime(dateObj),
                    // Toast.LENGTH_SHORT).show();

                    // Toast.makeText(getApplicationContext(), "Testing
                    // pickers"+dateObj.getYear()+"/"+dateObj.getMonth() +"/"+dateObj.getDay() + "
                    // "+timeObj.getHour() +":"+timeObj.getMin()+"", Toast.LENGTH_SHORT).show();
                    setAlarm(timeObj.futureTime(dateObj)); // change this to desired time to run the alarm
                    //

                    startActivity(intent);
                    finish();
                }
            }
        };
        // Listeners
        android.view.View.OnClickListener cancelListener = new View.OnClickListener() { // Done Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the cancel button");
                startActivity(intent);
                finish();
            }
        };

        android.view.View.OnClickListener popupListener = new View.OnClickListener() { // Date Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the calender button");
                initDatePicker();
                datePickerDialog.show();
            }
        };

        android.view.View.OnClickListener timeListener = new View.OnClickListener() { // Time Button Listener
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the clock button");
                initTimePicker();
                timePickerDialog.show();
            }
        };
        // set Listener
        btnDone.setOnClickListener(donelistener);
        btnCancel.setOnClickListener(cancelListener);
        btnDate.setOnClickListener(popupListener);
        btnTime.setOnClickListener(timeListener);
    }

    private void initDatePicker() { // should add to Date class
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year_here, int month_here, int day_here) {
                dateObj.setFutureDate(year_here, month_here, day_here);
                btnDate.setText(dateObj.makeDateString(dateObj.getDay(), dateObj.getMonth() + 1, dateObj.getYear()));
            }
        };
        year = this.cal.get(Calendar.YEAR);
        month = this.cal.get(Calendar.MONTH); // it starts at zero
        day = this.cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private void initTimePicker() { // should add to Time class
        TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour_here, int minutes_here) {
                timeObj.setFutureTime(hour_here, minutes_here);
                btnTime.setText((timeObj.makeTimeString(timeObj.getHour(), timeObj.getMin())));
            }
        };
        hour = this.cal.get(Calendar.HOUR);
        minutes = this.cal.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, timePickerListener, hour, minutes, true);
        hour = this.cal.get(Calendar.HOUR);
        minutes = this.cal.get(Calendar.MINUTE);
    }

    public void loadTask(TaskObj task) {
        this.txtName.setText(task.getTitle());
        this.urgentS.setChecked(task.getUrgent());
        this.btnDate.setText(task.getDate());
        this.btnTime.setText(task.getTime());
        this.dayBeforeS.setChecked(task.getBefore());
        this.txtNote.setText(task.getNote());
    }

    private void setAlarm(long time) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent testAlarm = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, testAlarm, PendingIntent.FLAG_IMMUTABLE);// change reques
                                                                                                     // code to
                                                                                                     // something uniqe
        // alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);//change
        // depending on importance
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        AlarmReceiver.notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        AlarmReceiver.activity = CreateTask.this;
    }
}