package com.ohiostate.pickup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ohiostate.pickup.DatabaseSchema.*;

public class DropFunctionality {

    private static DropFunctionality sDropFunctionality;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static final String TAG = "DropFunctionality";

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
                Log.d(TAG, "getDrop return null");
                Log.d(TAG, "getCount = " + cursor.getCount());
                return null;
            }

            Log.d(TAG, "getDrop return correct?");
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
        contentValues.put(DropTable.Cols.ID, drop.getId());
        contentValues.put(DropTable.Cols.PLAYER_ID, drop.getPlayer_id());
        contentValues.put(DropTable.Cols.SPORT, drop.getSport());
        contentValues.put(DropTable.Cols.LOCATION, drop.getLocation());
        contentValues.put(DropTable.Cols.PLAY_TIME, drop.getPlay_time().getTime());
        contentValues.put(DropTable.Cols.DIFFICULTY, drop.getDifficulty());
        contentValues.put(DropTable.Cols.DATE, drop.getDate().getTime());
        contentValues.put(DropTable.Cols.PLAYERS, drop.getNum_players());
        contentValues.put(DropTable.Cols.GENDER, drop.getGender());
        contentValues.put(DropTable.Cols.MESSAGE, drop.getMessage());

        return contentValues;
    }

    private DropCursorWrapper queryDrops(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(DropTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new DropCursorWrapper(cursor);
    }
}
