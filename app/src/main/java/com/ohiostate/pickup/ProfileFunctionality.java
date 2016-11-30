package com.ohiostate.pickup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.ohiostate.pickup.DatabaseSchema.PlayerTable;

/**
 * Created by blakehoward on 11/25/16.
 */

public class ProfileFunctionality {

    private static ProfileFunctionality sProfileFunctionality;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static final String TAG = "ProfileFunctionaltiy";

    public static ProfileFunctionality get(Context context){
        if(sProfileFunctionality == null){
            sProfileFunctionality = new ProfileFunctionality(context);
        }
        return sProfileFunctionality;
    }

    private ProfileFunctionality(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new PlayerDatabaseHelper(mContext).getWritableDatabase();
    }

    public void addPlayer(Player player){
        ContentValues values = getContentValues(player);
        mDatabase.insert(PlayerTable.NAME, null, values);
    }

    public Player getPlayer(long player_id){
        PlayerCursorWrapper cursor = queryPlayers(PlayerTable.Cols.ID + " = ?", new String[] {Long.toString(player_id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPlayer();
        } finally {
            cursor.close();
        }

    }

    public void updateProfle(Player player){
        long player_id = player.getId();
        ContentValues values = getContentValues(player);
        mDatabase.update(PlayerTable.NAME, values, PlayerTable.Cols.ID + " = ?", new String[] {Long.toString(player_id)});
    }

    private static ContentValues getContentValues(Player player){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlayerTable.Cols.ID, player.getId());
        contentValues.put(PlayerTable.Cols.FIRST_NAME,player.getFirst_Name());
        contentValues.put(PlayerTable.Cols.LAST_NAME,player.getLast_name());
        contentValues.put(PlayerTable.Cols.EMAIL, player.getEmail());
        contentValues.put(PlayerTable.Cols.GENDER, player.getGender());
        return contentValues;
    }

    private PlayerCursorWrapper queryPlayers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(PlayerTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new PlayerCursorWrapper(cursor);
    }

}
