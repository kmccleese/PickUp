package com.ohiostate.pickup;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

import static com.ohiostate.pickup.DatabaseSchema.*;

public class DropCursorWrapper extends CursorWrapper {
    public DropCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Drop getDrop() {
        int id = getInt(getColumnIndex(DropTable.Cols.ID));
        int playerIdString = getInt(getColumnIndex(DropTable.Cols.PLAYER_ID));
        String sport = getString(getColumnIndex(DropTable.Cols.SPORT));
        String location = getString(getColumnIndex(DropTable.Cols.LOCATION));
        long time = getLong(getColumnIndex(DropTable.Cols.PLAY_TIME));
        String difficulty = getString(getColumnIndex(DropTable.Cols.DIFFICULTY));
        long date = getLong(getColumnIndex(DropTable.Cols.DATE));
        int players = getInt(getColumnIndex(DropTable.Cols.PLAYERS));
        String gender = getString(getColumnIndex(DropTable.Cols.GENDER));
        String message = getString(getColumnIndex(DropTable.Cols.MESSAGE));

        Drop drop = new Drop(id);
        drop.setPlayer_id(playerIdString);
        drop.setSport(sport);
        drop.setLocation(location);
        drop.setPlay_time(new Date(time));
        drop.setDifficulty(difficulty);
        drop.setDate(new Date(date));
        drop.setNum_players(players);
        drop.setGender(gender);
        drop.setMessage(message);

        return drop;
    }
}
