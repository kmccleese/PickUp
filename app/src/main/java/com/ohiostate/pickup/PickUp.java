package com.ohiostate.pickup;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by blakehoward on 11/29/16.
 */

public class PickUp extends Application {
    private static PickUp instance;

    SQLiteDatabase playerDatabase;
    PlayerDatabaseHelper playerDatabaseHelper;
    ProfileFunctionality profileFunctionality;

    public static PickUp getInstance(){
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate(){
        Context context = getContext();
        playerDatabase = new PlayerDatabaseHelper(context).getWritableDatabase();
        instance = this;
        super.onCreate();
    }

}
