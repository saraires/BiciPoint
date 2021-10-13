package com.example.bicipoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Constants.CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }


    public long insertData(Note note){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_DATE,note.getDate());
        contentValues.put(Constants.COLUMN_TITLE,note.getTitle());
        contentValues.put(Constants.COLUMN_DESCRIPTION,note.getDescription());

        long status = sqLiteDatabase.insert(Constants.TABLE_NAME,null,contentValues);

        return status;
    }

    public List<Note> getAllNotes(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<Note> dataList           = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+Constants.TABLE_NAME,null);
        if (cursor.moveToFirst()){

            do {
                Note note = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(3),cursor.getString(2));

                dataList.add(note);


            }while (cursor.moveToNext());

        }


        return dataList;
    }



    public int updateData(Note note){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_DATE,note.getDate());
        contentValues.put(Constants.COLUMN_TITLE,note.getTitle());
        contentValues.put(Constants.COLUMN_DESCRIPTION,note.getDescription());

        int status = sqLiteDatabase.update(Constants.TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(note.getId())});


        return status;
    }

    public int deleteData(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int status = sqLiteDatabase.delete(Constants.TABLE_NAME,"id=?",new String[]{String.valueOf(id)});

        return status;

    }




}
