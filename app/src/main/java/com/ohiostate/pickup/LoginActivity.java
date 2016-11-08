package com.ohiostate.pickup;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.ohiostate.pickup.R;

public class LoginActivity extends Activity {
    private TextView info;
    private LoginButton loginButton;
    Intent intent;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
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
//        });
//    }


    CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Test","TestLog1234");
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());



        if (isLoggedIn()) {
            Log.d("","Logged In. Access token: " + AccessToken.getCurrentAccessToken());
            intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }


        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(
                        "Success: User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" + "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
                System.out.println(
                        "Success: User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" + "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );


            }

            @Override
            public void onCancel() {
                info.setText("onCancel: Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("onError: Login attempt failed.");
            }
        });



//        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {  });


        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
//        loginButton.setVisibility(View.INVISIBLE);
//        Intent intent = new Intent(LoginActivity.this, NewDropActivity.class);
//        startActivity(intent);
//        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d(" ","resultCode:" + resultCode);
        if (resultCode == RESULT_OK) {
            loginButton.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

}
