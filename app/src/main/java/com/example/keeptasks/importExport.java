package com.example.keeptasks;

public class importExport {
    private static final int REQUEST_WRITE_PERMISSION = 786;
    private String db_name = constants.DB_name;

    public importExport(){

    }
    
    // // TODO: grab the file after choosing it
    // private void fileChooser() {
    //     // requestPermission();
    //     Intent intent_file = new Intent(Intent.ACTION_GET_CONTENT);
    //     intent_file.setType("*/*");
    //     intent_file.addCategory(Intent.CATEGORY_OPENABLE);
    //     try {
    //         startActivityForResult(Intent.createChooser(intent_file, "Select a Database file"), 100);
    //     } catch (Exception e) {
    //         Log.d("BUTTONS", "No file explorer found");
    //     }
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

//    private void requestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[] { android.Manifest.permission.WRITE_EXTERNAL_STORAGE },
//                    REQUEST_WRITE_PERMISSION);
//        } else {
//            openFilePicker();
//        }
//    }
}
