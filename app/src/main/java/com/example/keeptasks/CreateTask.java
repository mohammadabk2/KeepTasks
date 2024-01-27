package com.example.keeptasks;

import static androidx.core.content.ContextCompat.getSystemService;

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
    private  AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        // Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); // return to Home Page
        // On Screen Objects
        Button btndn = (Button) findViewById(R.id.btnalltask); // The Done button
        Button btncln = (Button) findViewById(R.id.btncancel); // The Cancel button
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
        txtName.setHint("Task Title");
        txtNote.setHintTextColor(Color.WHITE);
        txtNote.setHint("Notes");
        //
        DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
        TaskObj task = MainActivity.task_held;
        if (task != null) {
            loadTask(task);
            dbHelper.complete_Task(task);
            MainActivity.task_held = null;
        }
        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() { // Done Button Listener
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Finish button");
                String Name = txtName.getText().toString();// Get the Title
                String Note = txtNote.getText().toString();// Get the Note
                String Date = btnDate.getText().toString();// Get the Date (Might change this to someother type late)
                String time = btnTime.getText().toString();// Get Time
                Boolean DateBefroe = dayBeforeS.isChecked();// Get Day Before status
                Boolean urgent = urgentS.isChecked();// Get Urgent status
                if (Name.matches("")) {// if Title is empty
                    Toast.makeText(getApplicationContext(), constants.name_Empty_Message, Toast.LENGTH_SHORT).show();
                } else {
                    // Added to DataBase
                    TaskObj task = new TaskObj(0, Name, Date, urgent, DateBefroe, Note, time);
                    boolean success = dbHelper.addOne(task, DataBaseHelper.table_name);

                    // testing a notfication
//                    if (!Permissions.notficationPermission(getApplicationContext(), CreateTask.this)) {
//                        Toast.makeText(getApplicationContext(),
//                                "Notfications " + constants.permissionDenied + constants.allowPermission,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    NotificationManager notificationManager = (NotificationManager) getSystemService(
//                            Context.NOTIFICATION_SERVICE);
//                    Notfication.makeNotification(getApplicationContext(), CreateTask.this, Name + time, Name, Note,
//                            Color.BLACK,
//                            true, notificationManager, MainActivity.class);
                    // testing alarm
                    setAlarm(cal.getTimeInMillis()); // change this to desired time to run the alarm

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

    private void initDatePicker() { // should add to Time class
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

    private void initTimePicker() { // should add to Time class
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

    public void loadTask(TaskObj task) {
        this.txtName.setText(task.getTitle());
        this.urgentS.setChecked(task.getUrgent());
        this.btnDate.setText(task.getDate());
        this.btnTime.setText(task.getTime());
        this.dayBeforeS.setChecked(task.getBefore());
        this.txtNote.setText(task.getNote());
    }

    private  void setAlarm(long time){
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent testalarm = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,testalarm, PendingIntent.FLAG_IMMUTABLE);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);//change depending on importance
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,time,pendingIntent);
//        Toast.makeText(getApplicationContext(), "In setAlarm",Toast.LENGTH_SHORT).show();
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        AlarmReceiver.notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        AlarmReceiver.activity = CreateTask.this;
    }
}