package com.example.airlinereservationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlite extends SQLiteOpenHelper {
    private final String Table_name = "SignUp";
    private final String COL1 = "Email";
    private final String COL2 = "Password";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User";

    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTable = "CREATE TABLE " + Table_name +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL1 + " TEXT," + COL2 + " TEXT)";
        sqLiteDatabase.execSQL(CreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ Table_name);
        onCreate(sqLiteDatabase);
    }

    public long SaveData(String email, String Pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Email", email);
        cv.put("Password", Pass);
        long r = db.insert(Table_name, null, cv);
        return r;
    }

    public String ViewData()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from SignUp", null);
        String record="";
        while (cursor.moveToNext())
        {       String uname= cursor.getString(1);
            String upass= cursor.getString(2);
            record = record+ uname + "-" + upass + "\n";   }
        return record;}


    public String Search(String name)
    {SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from SignUp where UserName = ?", new String[]{name} );
        String record = "";
        while (cursor.moveToNext()) {
            String uname = cursor.getString(1);
            String upass = cursor.getString(2);
            if(uname.equals(name))
                record = record + uname + "-" + upass + "\n";
//                record = 1;
        }
        return record;
    }


//    public boolean UpdateData(String name,String pass )
//    {SQLiteDatabase db= this.getWritableDatabase();
//        ContentValues cv= new ContentValues();
//        cv.put("UserName", name);
//        cv.put("Password", pass);
//        long r = 0;
//        Cursor cursor= db.rawQuery("select * from Login where UserName = ?" ,
//                new String[]{name} );
//        if(cursor.getCount()>0) {
//            r = db.update("Login", cv, "UserName = ?", new String[]{name});
//        }
//        if (r>0)
//            return true;
//        else
//            return false;
//    }
//
//    public boolean DeleteData(String name )
//    {SQLiteDatabase db= this.getWritableDatabase();
//        long r = 0;
//        Cursor cursor= db.rawQuery("select * from Login where UserName = ?"
//                , new String[]{name} );
//        if(cursor.getCount()>0) {
//            r= db.delete("Login", "UserName = ?", new String[]{name});   }
//        if (r>0)
//            return true;
//        else
//            return false;}








}
