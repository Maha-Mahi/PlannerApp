package com.example.plannerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register";
    public static final String TABLE_NAME="registered";
    public static final String CLO_1="ID";
    public static final String CLO_2="username";
    public static final String CLO_3="password";
    private static final String TAG = "DatabaseHelper";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + CLO_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + CLO_2 + " TEXT,"
                + CLO_3 + " TEXT" + ")";
        db.execSQL(createTable);
        // String createtable=
        //db.execSQL("CREATE TABLE registrered(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put(COL1, newEntry);
        contentValues.put(CLO_2, user);
        contentValues.put(CLO_3, password);



        // Log.d(TAG, "addData: Adding " + newEntry + " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + user+ " to " + TABLE_NAME);
        Log.d(TAG, "addData: Adding " + password + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return  result;
        //if date as inserted incorrectly it will return -1

        /*SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res= db.insert ("registered",null,contentValues);
        db.close();
        return res;*/
    }

    public boolean checkUser(String username, String password){

        String sql="Select count(*) from registered where username='"+ username+ "'and password='"+password+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        statement.close();

        if (l==1){
            return true;
        }
        else {
            return false;
        }


       /* String[] coulumns={CLO_1};
        SQLiteDatabase db=getReadableDatabase();
        String selection= CLO_2 + "m?" + "and" + CLO_3 +"m?";
        String[] selectionArgs = { username , password};
        //query
        Cursor cursor=db.query(TABLE_NAME,coulumns,selection,selectionArgs,null,null,null);
        int count=cursor.getCount();
        db.close();

        if(count>0)
            return  true;
        else
            return false;*/

    }
}

