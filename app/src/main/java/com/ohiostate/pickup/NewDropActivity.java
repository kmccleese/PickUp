package com.ohiostate.pickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import pickup.R;

public class NewDropActivity extends AppCompatActivity {

    private static final String TAG = "NewDropActivity";
    private Button mDropButton;
    private Button mShareButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "NewDropActivity onCreate() called");
        setContentView(R.layout.activity_new_drop);

        // instantiate
        mDropButton = (Button) findViewById(R.id.drop_button);
        mShareButton = (Button) findViewById(R.id.share_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);

        // listeners
        mDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create the drop
            }
        });
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share drop to Facebook
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel the creation of this drop
            }
        });
    }
}
