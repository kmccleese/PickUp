package com.ohiostate.pickup;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class ViewDropActivityFragment extends Fragment {

    private static final String ARG_DROP = "drop_id";
    private Button mPickUpButton;
    private Button mEditButton;
    private Button mDeleteButton;
    private ImageView mPhotoView;
    private TextView mNameTextView;
    private String mDrop;
    private TextView mDateTextView;
    private TextView mTimeTextView;

    public static ViewDropActivityFragment newInstance(String dropID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DROP, dropID);

        ViewDropActivityFragment fragment = new ViewDropActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrop = (String) getArguments().getSerializable(ARG_DROP);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_view_drop, container, false);

        // instantiate
        mPickUpButton = (Button) v.findViewById(R.id.pickup_button);
        mEditButton = (Button) v.findViewById(R.id.edit_button);
        mDeleteButton = (Button) v.findViewById(R.id.delete_button);
        mPhotoView = (ImageView) v.findViewById(R.id.photo);
        mNameTextView = (TextView) v.findViewById(R.id.drop_name_text);
        mDateTextView = (TextView) v.findViewById(R.id.drop_date_text);
        mTimeTextView = (TextView) v.findViewById(R.id.drop_time_text);

        // set TextViews
        mNameTextView.setText(mDrop);
        mDateTextView.setText("Month, Day, Year");
        mTimeTextView.setText("Time");

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_drop_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_drop:
                // go to new drop
                Intent intent = new Intent(getActivity(), NewDropActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_view_profile:
                // go to view profile
                Intent intent1 = new Intent(getActivity(), ViewProfile.class);
                startActivity(intent1);
                return true;
            case R.id.menu_item_edit_profile:
                // go to edit profile
                Intent intent2 = new Intent(getActivity(), EditProfile.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
