package com.ohiostate.pickup;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import pickup.R;

public class ViewDropActivityFragment extends Fragment {

    private static final String TAG = "ViewDropActivityFragment";
    private Button mPickUpButton;
    private Button mEditButton;
    private Button mDeleteButton;
    private ImageView mPhotoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_view_drop, container, false);

        // instantiate
        mPickUpButton = (Button) v.findViewById(R.id.pickup_button);
        mEditButton = (Button) v.findViewById(R.id.edit_button);
        mDeleteButton = (Button) v.findViewById(R.id.delete_button);
        mPhotoView = (ImageView) v.findViewById(R.id.photo);

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

        return v;
    }
}
