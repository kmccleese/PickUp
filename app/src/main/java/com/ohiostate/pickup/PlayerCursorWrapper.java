package com.ohiostate.pickup;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.facebook.Profile;
import com.ohiostate.pickup.DatabaseSchema.PlayerTable;

/**
 * Created by blakehoward on 11/23/16.
 */

public class PlayerCursorWrapper extends CursorWrapper {
    // Constructor
    public PlayerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Player getPlayer(){
        long playerID = getLong(getColumnIndex(PlayerTable.Cols.PLAYER_ID));
        String playerFirstName = getString(getColumnIndex(PlayerTable.Cols.FIRST_NAME));
        String playerLastName = getString(getColumnIndex(PlayerTable.Cols.LAST_NAME));
        String playerEmail = getString(getColumnIndex(PlayerTable.Cols.EMAIL));
        String playerGender = getString(getColumnIndex(PlayerTable.Cols.GENDER));

        Player player = new Player(Long.parseLong(Profile.getCurrentProfile().getId()));
//        ProfileFunctionality profileFunctionality = ProfileFunctionality.get(PickUp.getContext());
//
//        player = profileFunctionality.getPlayer(playerID);
//        if (player != null) {

            player.setId(playerID);
            player.setFirst_name(playerFirstName);
            player.setLast_name(playerLastName);
            player.setEmail(playerEmail);
            player.setGender(playerGender);
//        }
//        else {
//            player = new Player(Long.parseLong(Profile.getCurrentProfile().getId()));
//        }

        return player;
    }
}
