package com.ohiostate.pickup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;


public class LauncherActivity extends AppCompatActivity {

    AccessTokenTracker accessTokenTracker;
    Intent intent;



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
            intent = new Intent(LauncherActivity.this, MainActivity.class);
            LauncherActivity.this.startActivity(intent);
            finish();
        }
        else{
            Log.d("","NOT Logged In. Access token: " + AccessToken.getCurrentAccessToken());
            intent = new Intent(LauncherActivity.this, LoginActivity.class);
            LauncherActivity.this.startActivity(intent);
            finish();
        }



    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
