package com.example.keeptasks;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import java.util.ArrayList;

public class menuPopUp {
    public static void listPopupDelete(Context context, View v){
        // create all the menu items
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.listmenue, popupMenu.getMenu());
        ArrayList<String> list = dbHelper.getList();
        for (int i = 0; i < list.size(); i++) {// adds the lists to the menu
            popupMenu.getMenu().add(list.get(i));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                for (int i = 0; i < list.size(); i++) {
                    if (item.getTitle().equals(list.get(i))) {
                        dbHelper.removeFromList(item.getTitle().toString());
                        popupMenu.getMenu().removeItem(i);
                        list.remove(i);
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public static void listPopupChoose(Context context, View v){
        // create all the menu items
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.listmenue, popupMenu.getMenu());
        ArrayList<String> list = dbHelper.getList();
        for (int i = 0; i < list.size(); i++) {// adds the lists to the menu
            popupMenu.getMenu().add(list.get(i));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                for (int i = 0; i < list.size(); i++) {
                    if (item.getTitle().equals(list.get(i))) {
                        lists.nameOfList =(String) item.getTitle();
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
