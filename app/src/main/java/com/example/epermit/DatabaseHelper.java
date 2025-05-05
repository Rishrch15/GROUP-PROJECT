package com.example.epermit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EPermit.db";
    private static final int DATABASE_VERSION = 1;

    // User table
    private static final String TABLE_USERS = "users";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_PASSWORD = "password";

    // Transfer requests table
    private static final String TABLE_TRANSFER = "transfer_requests";
    private static final String COL_EVENT = "event";
    private static final String COL_DATE = "date";
    private static final String COL_FROM = "location_from";
    private static final String COL_THINGS = "things";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE " + TABLE_USERS + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EMAIL + " TEXT," +
                COL_STUDENT_ID + " TEXT UNIQUE," +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createUsers);

        String createTransfer = "CREATE TABLE " + TABLE_TRANSFER + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EVENT + " TEXT," +
                COL_DATE + " TEXT," +
                COL_FROM + " TEXT," +
                COL_THINGS + " TEXT)";
        db.execSQL(createTransfer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSFER);
        onCreate(db);
    }

    public boolean registerUser(String email, String studentId, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_STUDENT_ID, studentId);
        values.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean login(String studentId, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                COL_STUDENT_ID + "=? AND " + COL_PASSWORD + "=?",
                new String[]{studentId, password}, null, null, null);
        return cursor.getCount() > 0;
    }

    public boolean submitTransfer(String event, String date, String from, String things) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EVENT, event);
        values.put(COL_DATE, date);
        values.put(COL_FROM, from);
        values.put(COL_THINGS, things);
        long result = db.insert(TABLE_TRANSFER, null, values);
        return result != -1;
    }
}
