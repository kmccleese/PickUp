package com.ohiostate.pickup;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.util.ThreadUtil;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

//import pickup.R;

public class LoginActivity extends Activity {

    private PlayerDatabaseHelper dh;
    private TextView info;
    private LoginButton loginButton;

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
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        this.dh = new PlayerDatabaseHelper(this);

        setContentView(R.layout.activity_login);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "location"));

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                info.setText(
                        "Success: User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" + "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
                GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                           String email = object.getString("email");
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String gender = object.getString("gender");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name, gender, location, email");
                request.setParameters(parameters);
                request.executeAsync();
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


  /*      super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            startActivity(new Intent(this, MainActivity.class));
        }
    }


}
