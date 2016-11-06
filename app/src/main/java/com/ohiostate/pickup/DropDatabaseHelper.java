package com.ohiostate.pickup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ohiostate.pickup.DatabaseSchema.DropTable;

public class DropDatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "DropDB.db";

    public DropDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DropTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DropTable.Cols.PLAYER_ID + ", " +
                DropTable.Cols.SPORT + ", " +
                DropTable.Cols.LOCATION + ", " +
                DropTable.Cols.PLAY_TIME +", " +
                DropTable.Cols.DIFFICULTY + ", " +
                DropTable.Cols.SUBMIT_TIME + ", " +
                DropTable.Cols.PLAYERS + ", " +
                DropTable.Cols.GENDER + ", " +
                DropTable.Cols.ID +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DropTable");
        onCreate(db);
    }

    public boolean insertDrop(String name, String email, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
        db.insert("PlayerTable", null, contentValues);
        return true;
    }

    public boolean updatePlayer(Integer id, String name, String email, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
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
