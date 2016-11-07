package com.ohiostate.pickup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ohiostate.pickup.DatabaseSchema.*;

public class DropFunctionality {

    private static DropFunctionality sDropFunctionality;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DropFunctionality get(Context context) {
        if(sDropFunctionality == null) {
            sDropFunctionality = new DropFunctionality(context);
        }
        return sDropFunctionality;
    }

    private DropFunctionality(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DropDatabaseHelper(mContext).getWritableDatabase();
    }

    public void addDrop(Drop drop) {
        ContentValues values = getContentValues(drop);
        mDatabase.insert(DropTable.NAME, null, values);
    }

    public List<Drop> getDrops() {
        List<Drop> drops = new ArrayList<>();
        DropCursorWrapper cursor = queryDrops(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                drops.add(cursor.getDrop());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return drops;
    }

    public Drop getDrop(int id) {
        DropCursorWrapper cursor = queryDrops(DropTable.Cols.ID + " = ?", new String[] { Integer.toString(id) });

        try {
            if(cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getDrop();
        } finally {
            cursor.close();
        }
    }

    public void updateDrop(Drop drop) {
        int id = drop.getId();
        ContentValues values = getContentValues(drop);
        mDatabase.update(DropTable.NAME, values, DropTable.Cols.ID + " = ?", new String[] { Integer.toString(id) });
    }

    private static ContentValues getContentValues(Drop drop) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_id", drop.getPlayer_id());
        contentValues.put("sport", drop.getSport());
        contentValues.put("location", drop.getLocation());
        contentValues.put("play_time", drop.getPlay_time().getTime());
        contentValues.put("difficulty_level", drop.getDifficulty());
        contentValues.put("date", drop.getDate().getTime());
        contentValues.put("num_players", drop.getNum_players());
        contentValues.put("preferred_gender", drop.getGender());
        contentValues.put("message", drop.getMessage());

        return contentValues;
    }

    private DropCursorWrapper queryDrops(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(DropTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new DropCursorWrapper(cursor);
    }
}
