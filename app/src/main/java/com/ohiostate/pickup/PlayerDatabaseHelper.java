package com.ohiostate.pickup;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ohiostate.pickup.DatabaseSchema.PlayerTable;

public class PlayerDatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "PlayerDatabase.db";

    public PlayerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PlayerTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                PlayerTable.Cols.FIRST_NAME + ", " +
                PlayerTable.Cols.LAST_NAME + ", " +
                PlayerTable.Cols.EMAIL + ", " +
                PlayerTable.Cols.GENDER +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PlayerTable");
        onCreate(db);

    }

    public boolean insertPlayer(String first_name, String last_name, String email, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
        db.insert("PlayerTable", null, contentValues);
        return true;
    }

    public boolean updatePlayer(Integer id, String first_name, String last_name, String email, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
        db.update("PlayerTable", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deletePlayer(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PlayerTable",
                "id = ? ",
                new String[]{Integer.toString(id)});

    }
}
