package com.ohiostate.pickup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;

import org.w3c.dom.Text;

import java.util.List;

public class ViewProfileFragment extends Fragment {

    private static final String ARG_Profile = "player_id";
    public static final String TAG = "ViewProfileFrag";

    private TextView mFirstName;
    private TextView mLastName;
    private TextView mEmail;
    private TextView mGender;
    private Player mPlayer;
    private Button mEditProfileButton;

    public static ViewProfileFragment newInstance(int playerID){
        Bundle args = new Bundle();
        args.putInt(ARG_Profile, playerID);

        ViewProfileFragment fragment = new ViewProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long playerID = Long.parseLong(Profile.getCurrentProfile().getId());

        Log.d(TAG,"ID: " + playerID);

        ProfileFunctionality profileFunctionality = ProfileFunctionality.get(getActivity());
        mPlayer = profileFunctionality.getPlayer(playerID);


//
//        List<Player> players = profileFunctionality.getPlayers();
//        int count = 0;
//
//        for (count = 0; count<players.size(); count++){
//            long currentPlayerID = players.get(count).getId();
//            if(currentPlayerID == playerID){
//                mPlayer = players.get(count);
//                break;
//            }
//        }
      setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_view_profile, container, false);

        mFirstName = (TextView) v.findViewById(R.id.player_name);
        mEmail = (TextView) v.findViewById(R.id.player_email);
        mGender = (TextView) v.findViewById(R.id.player_gender);


        if (mPlayer == null) {
            mPlayer = new Player(Long.parseLong(Profile.getCurrentProfile().getId()));
            mFirstName.setText("Name");
            mEmail.setText("Email");
            mGender.setText("Gender");
        }

        else {
            if (mPlayer.first_name != null) {
                mFirstName.setText(mPlayer.first_name);
            } else {
                mFirstName.setText("Name");
            }
            if (mPlayer.email != null) {
                mEmail.setText(mPlayer.email);
            } else {
                mPlayer.setEmail("Email");
            }
            if(mPlayer.gender != null) {
                mGender.setText(mPlayer.gender);
            }
            else{
                mGender.setText("Gender");
            }

        }

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
