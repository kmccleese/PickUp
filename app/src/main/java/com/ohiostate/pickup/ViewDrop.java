package com.ohiostate.pickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import pickup.R;

public class ViewDrop extends AppCompatActivity {

    private static final String TAG = "ViewDrop";
    private Button mPickUpButton;
    private Button mEditButton;
    private Button mDeleteButton;
    private ImageView mPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ViewDrop onCreate called");
        setContentView(R.layout.activity_view_drop);

        // instantiate
        mPickUpButton = (Button) findViewById(R.id.pickup_button);
        mEditButton = (Button) findViewById(R.id.edit_button);
        mDeleteButton = (Button) findViewById(R.id.delete_button);
        mPhotoView = (ImageView)findViewById(R.id.photo);

        // listeners

        mPickUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // PickUp drop
            }
        });
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creator of drop can edit the drop
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creator of drop can delete the drop
            }
        });
    }
}
