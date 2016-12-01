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
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewDropActivityFragment extends Fragment {

    private static final String ARG_DROP = "drop_id";
    public static final String TAG = "ViewDropActivityFrag";
    private Button mPickUpButton;
    private Button mEditButton;
    private Button mDeleteButton;
    private ImageView mPhotoView;
    private TextView mNameTextView;
    private Drop mDrop;
    private TextView mDateTextView;
    private TextView mTimeTextView;
    private TextView mMessageTextView;
    private TextView mSportTextView;
    private TextView mDifficultyTextView;
    private TextView mNumPlayersTextView;
    private TextView mGenderTextView;

    public static ViewDropActivityFragment newInstance(int dropID) {
        Bundle args = new Bundle();
        args.putInt(ARG_DROP, dropID);

        ViewDropActivityFragment fragment = new ViewDropActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int dropId = getArguments().getInt(ARG_DROP);
        Log.d(TAG, "onCreate dropId = " + dropId);
        DropFunctionality dropFunctionality = DropFunctionality.get(getActivity());
        List<Drop> drops = dropFunctionality.getDrops();
        int count = 0;
        for(count = 0; count < drops.size(); count++) {
            int currentDropId = drops.get(count).getId();
            if(currentDropId == dropId) {
                mDrop = drops.get(count);
                break;
            }
        }
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
        mMessageTextView = (TextView) v.findViewById(R.id.drop_message_text);
        mSportTextView = (TextView) v.findViewById(R.id.drop_sport_text);
        mDifficultyTextView = (TextView) v.findViewById(R.id.drop_difficulty_text);
        mNumPlayersTextView = (TextView) v.findViewById(R.id.drop_num_players_text);
        mGenderTextView = (TextView) v.findViewById(R.id.drop_gender_text);

        // set TextViews
        mNameTextView.setText(Integer.toString(mDrop.getPlayer_id()));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        String date = " " + dateFormatter.format(mDrop.getDate());
        DateFormat dateFormat = DateFormat.getTimeInstance();
        String time = " " + dateFormat.format(mDrop.getPlay_time());
        mDateTextView.setText(date + time);
        mSportTextView.setText(mDrop.getSport());
        mMessageTextView.setText(mDrop.getMessage());
        mDifficultyTextView.setText(mDrop.getDifficulty());
        mNumPlayersTextView.setText(Integer.toString(mDrop.getNum_players()));
        mGenderTextView.setText(mDrop.getGender());

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
                Intent intent = new Intent(getActivity(), NewDropActivity.class);
                startActivity(intent);
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
            case R.id.menu_item_logout:
                LoginManager.getInstance().logOut();
                Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
