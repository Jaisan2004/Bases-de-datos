package com.example.mysqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.example.mysqlite.clases.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UserDAO {
    private ManagerDB managerDB;
    Context context;
    View view;
    User user;

    public UserDAO(Context context, View view) {
        this.context = context;
        this.view = view;
        managerDB = new ManagerDB(context);
    }

    public void insertUser(User myUser) {
        try {
            SQLiteDatabase db = managerDB.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("usu_document", myUser.getDocument());
                values.put("usu_user", myUser.getUser());
                values.put("usu_names", myUser.getNames());
                values.put("usu_last_names", myUser.getLastNames());
                values.put("usu_pass", myUser.getPass());
                values.put("usu_status", myUser.getStatus());
                long cod = db.insert("users", null, values);
                Snackbar.make(this.view, "The user register success: " + cod, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(this.view, "the user not register ERROR: ", Snackbar.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Log.i("ERROR", "" + e);
        }
    }

    public ArrayList<User> getUserList(){
        SQLiteDatabase db = managerDB.getReadableDatabase();
        String query ="select * from users";
        ArrayList<User> userList = new ArrayList<User>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                user = new User();
                user.setDocument(cursor.getInt(0));
                user.setUser(cursor.getString(1));
                user.setNames(cursor.getString(2));
                user.setLastNames(cursor.getString(3));
                user.setPass(cursor.getString(4));
                user.setStatus(cursor.getInt(5));
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public ArrayList<User> Buscar(String searchTerm) {
        SQLiteDatabase db = managerDB.getReadableDatabase();
        String query = "SELECT * FROM users WHERE " +
                "usu_document LIKE '%" + searchTerm + "%' OR " +
                "usu_user LIKE '%" + searchTerm + "%' OR " +
                "usu_names LIKE '%" + searchTerm + "%' OR " +
                "usu_last_names LIKE '%" + searchTerm + "%' OR " +
                "usu_pass LIKE '%" + searchTerm + "%';";
        ArrayList<User> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setDocument(cursor.getInt(0));
                user.setUser(cursor.getString(1));
                user.setNames(cursor.getString(2));
                user.setLastNames(cursor.getString(3));
                user.setPass(cursor.getString(4));
                user.setStatus(cursor.getInt(5));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}
