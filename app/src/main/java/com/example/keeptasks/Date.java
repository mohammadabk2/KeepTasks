package com.example.keeptasks;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Date {

   private int day, month, year;

   public Date() {
   }

   public String makeDateString(int day, int month, int year) {
       return day + " " + month + " " + year;
   }

   public String getTodayDate() {
       Calendar cal = Calendar.getInstance();
       this.year = cal.get(Calendar.YEAR);
       this.month = cal.get(Calendar.MONTH) + 1;
       this.day = cal.get(Calendar.DAY_OF_MONTH);
       return makeDateString(day, month, year);
   }

}
