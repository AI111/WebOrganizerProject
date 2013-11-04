package com.example.weborganizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sasha on 04.11.13.
 */
public class DatabaseWorker extends SQLiteOpenHelper{
    static String DATABASE_NAME="ClientDB";
    private static String PERSON_TABLE="CREATE TABLE User(id_User INTEGER PRIMARY KEY, " +
            "email CHAR(50), pass char(20))";
    private static String TASKS = "CREATE TABLE Task(id_User INTEGER, Task_title VARCHAR(80), Text TEXT," +
            "Time DATETIME, Last_Editing DATETIME, Editing_Type TINYINT, Filter_Id TINYINT)";
    private static String FILTERS = "CREATE TABLE Filters ( Filter_Name VARCHAR(30), TINYINT INTEGER PRIMARY KEY)";
    private static String CONTACTS = "CREATE TABLE Contacts ( Filter_Name VARCHAR(30), TINYINT INTEGER PRIMARY KEY)";
    private static String USER_OPTIONS = "CREATE TABLE UserOptions ( User_id INTEGER, Last_Sync_Date DATETIME)";
    public DatabaseWorker(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PERSON_TABLE);
        db.execSQL(TASKS);
        db.execSQL(CONTACTS);
        db.execSQL(FILTERS);
        db.execSQL(USER_OPTIONS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+PERSON_TABLE);
        db.execSQL("DROP TABLE "+TASKS);
        db.execSQL("DROP TABLE "+CONTACTS);
        db.execSQL("DROP TABLE "+FILTERS);
        db.execSQL("DROP TABLE"+USER_OPTIONS);
        onCreate(db);

    }
}
