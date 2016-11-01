package com.ohiostate.pickup;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pickup.R;

public class NewDropActivityFragment extends Fragment {

    private static final String TAG = "NewDropActivity";
    private Button mDropButton;
    private Button mShareButton;
    private Button mCancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "NewDropActivity onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_new_drop, container, false);

        // instantiate
        mDropButton = (Button) v.findViewById(R.id.drop_button);
        mShareButton = (Button) v.findViewById(R.id.share_button);
        mCancelButton = (Button) v.findViewById(R.id.cancel_button);

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

        return v;
    }
}
