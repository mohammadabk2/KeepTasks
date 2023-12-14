package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;

import com.example.keeptasks.MainActivity;

public class readDB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        // Database
        // try (Statement stmt = con.createStatement()) {
        //     ResultSet rs = stmt.executeQuery("SELECT title, urgent, date, remind_before, note from tasks");
        //     while (rs.next()) {
        //         String coffeeName = rs.getString("COF_NAME");
        //         int supplierID = rs.getInt("SUP_ID");
        //         float price = rs.getFloat("PRICE");
        //         int sales = rs.getInt("SALES");
        //         int total = rs.getInt("TOTAL");
        //         System.out.println(coffeeName + ", " + supplierID + ", " + price +
        //                 ", " + sales + ", " + total);
        //     }
        // } catch (SQLException e) {
        //     Log.d("BUTTONS", "DataBase read error");
        // }
        // Intent
        // Intent intent = new Intent(getApplicationContext(), MainActivity.class); //
        // return to Home Page
        // On Screen Objects
        Button btndn = (Button) findViewById(R.id.btnalltask); // The Finish button
        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() { // Finish Button Listener
            public void onClick(View v) {
                // startActivity(intent);
                finish();
            }
        };
        btndn.setOnClickListener(donelistener);
    }
}
