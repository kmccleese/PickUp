package com.ohiostate.pickup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import pickup.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mSearchButton;
    private Button mProfileButton;
    private Button mViewDropButton;
    private Button mNewDropButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity onCreate() called");
        setContentView(R.layout.activity_main);

        mProfileButton = (Button) findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Start activity
            }
        });
        mSearchButton = (Button) findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Start activity

            }
        });

        mViewDropButton = (Button) findViewById(R.id.view_drop_button);
        mViewDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start ViewDropActivityFragment
                Intent i = new Intent(MainActivity.this, ViewDropActivityFragment.class);
                startActivity(i);
            }
        });

        mNewDropButton = (Button) findViewById(R.id.new_drop_button);
        mNewDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start NewDropActivity
                Intent i = new Intent(MainActivity.this, NewDropActivity.class);
                startActivity(i);
            }
        });
    }


}
