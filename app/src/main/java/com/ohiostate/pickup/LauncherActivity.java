package com.ohiostate.pickup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.util.Log;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;


public class LauncherActivity extends AppCompatActivity {

    AccessTokenTracker accessTokenTracker;
    Intent intent;
    ProfileTracker mProfileTracker;
    CallbackManager mCallbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        FacebookSdk.sdkInitialize(getApplicationContext());

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//      });


        if (isLoggedIn()) {
            Log.d("","Logged In. Access token: " + AccessToken.getCurrentAccessToken());
            long playerID = Long.parseLong(Profile.getCurrentProfile().getId());
            Intent intent = MainActivity.newIntent(this, playerID);
            startActivity(intent);
            finish();
        }
        else{
            Log.d("","NOT Logged In. Access token: " + AccessToken.getCurrentAccessToken());
            intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }



    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if you don't add following block,
        // your registered `FacebookCallback` won't be called
        if (mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }
}
