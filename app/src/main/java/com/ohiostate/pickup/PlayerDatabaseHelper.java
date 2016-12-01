package com.ohiostate.pickup;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                PlayerTable.Cols.PLAYER_ID + ", " +
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

    public boolean insertPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_id",player.getId());
        contentValues.put("first_name", player.getFirst_Name());
        contentValues.put("last_name", player.getLast_name());
        contentValues.put("email", player.getEmail());
        contentValues.put("gender", player.getGender());
        db.insert("PlayerTable", null, contentValues);
        return true;
    }

    public boolean updatePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_id",player.getId());
        contentValues.put("first_name", player.getFirst_Name());
        contentValues.put("last_name", player.getLast_name());
        contentValues.put("email", player.getEmail());
        contentValues.put("gender", player.gender);
        db.update(PlayerTable.NAME, contentValues, PlayerTable.Cols.PLAYER_ID + " = ? ", new String[]{Long.toString(player.player_id)});
        return true;
    }

    public Integer deletePlayer(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PlayerTable",
                "id = ? ",
                new String[]{Integer.toString(id)});

    }

    public boolean isExist(long possibleID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + PlayerTable.NAME + " WHERE player_id = '" + possibleID + "'", null);
        boolean exist = (cur.getCount() > 0);
        cur.close();
        db.close();
        return exist;

    }
}
