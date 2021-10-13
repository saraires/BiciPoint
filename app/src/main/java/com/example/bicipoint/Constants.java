package com.example.bicipoint;

import android.database.sqlite.SQLiteDatabase;

public class Constants {


    public static final String DATABASE_NAME      = "login.db";
    public static final int DATABASE_VERSION      = 2;
    public static final String TABLE_NAME         = "notes";
    public static final String COLUMN_ID          = "id";
    public static final String COLUMN_TITLE       = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE        = "date";


    public static String CREATE_TABLE_QUERY ="CREATE TABLE "+TABLE_NAME+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_TITLE+" TEXT, "
            +COLUMN_DATE+" TEXT, "
            +COLUMN_DESCRIPTION+ " TEXT"
            +")";







}
