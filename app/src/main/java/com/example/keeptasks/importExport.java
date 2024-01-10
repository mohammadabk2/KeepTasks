package com.example.keeptasks;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

public class importExport {


   public static String saveDB(String path) {

       try {
//           File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) ,constants.dir);
//           if(!dir.exists()) {
//               dir.mkdirs();
//           }
           File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) ,path);
           f.createNewFile();
           return "T";
       } catch (Exception e) {
           return  "F";
       }
//       return  false;
   }


    // // TODO: grab the file after choosing it
    // private void fileChooser() {
    // // requestPermission();
    // Intent intent_file = new Intent(Intent.ACTION_GET_CONTENT);
    // intent_file.setType("*/*");
    // intent_file.addCategory(Intent.CATEGORY_OPENABLE);
    // try {
    // startActivityForResult(Intent.createChooser(intent_file, "Select a Database
    // file"), 100);
    // } catch (Exception e) {
    // Log.d("BUTTONS", "No file explorer found");
    // }
    // }

    // private void requestPermission() {
    // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    // requestPermissions(new String[] {
    // android.Manifest.permission.WRITE_EXTERNAL_STORAGE },
    // REQUEST_WRITE_PERMISSION);
    // } else {
    // fileChooser();
    // }
    // }

    // private void requestPermission() {
    // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    // requestPermissions(new String[] {
    // android.Manifest.permission.WRITE_EXTERNAL_STORAGE },
    // REQUEST_WRITE_PERMISSION);
    // } else {
    // openFilePicker();
    // }
    // }
}
