package com.ohiostate.pickup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ohiostate.pickup.DatabaseSchema.DropTable;

public class DropDatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "dropDB.db";

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
                DropTable.Cols.DATE + ", " +
                DropTable.Cols.PLAYERS + ", " +
                DropTable.Cols.GENDER + ", " +
                DropTable.Cols.ID + ", " +
                DropTable.Cols.MESSAGE +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DropTable");
        onCreate(db);
    }
/*
    public boolean insertDrop(Drop drop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_id", drop.getPlayer_id());
        contentValues.put("sport", drop.getSport());
        contentValues.put("location", drop.getLocation());
       // contentValues.put("play_time", drop.getPlay_time());
        contentValues.put("difficulty_level", drop.getDifficulty());
       // contentValues.put("date", drop.getDate());
        contentValues.put("num_players", drop.getNum_players());
        contentValues.put("preferred_gender", drop.getGender());
        db.insert(DropTable.NAME, null, contentValues);
        return true;
    }

    public boolean updateDrop(Drop drop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_id", drop.getPlayer_id());
        contentValues.put("sport", drop.getSport());
        contentValues.put("location", drop.getLocation());
        contentValues.put("play_time", drop.getPlay_time());
        contentValues.put("difficulty_level", drop.getDifficulty());
        contentValues.put("date", drop.getDate());
        contentValues.put("num_players", drop.getNum_players());
        contentValues.put("preferred_gender", drop.getGender());
        Integer id = drop.getId();
        db.update(DropTable.NAME, contentValues, DropTable.Cols.ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteDrop(Drop drop) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer id = drop.getId();
        return db.delete("DropTable",
                "id = ? ",
                new String[]{Integer.toString(id)});

    }
    */
}
