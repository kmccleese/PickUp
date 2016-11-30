package com.ohiostate.pickup;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import com.ohiostate.pickup.PlayerCursorWrapper;

public class EditProfileFragment extends Fragment {

    private GoogleApiClient mClient;
    private Player mPlayer;
    private Button mCancelButton;
    private Button mSaveButton;
    private Spinner genderSpinner;
    private String mGender;
    private TextView mEmail;
    private TextView mName;
    private Context context;
    PlayerDatabaseHelper playerDBHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        playerDBHelper = new PlayerDatabaseHelper(context);
        ProfileFunctionality profileFunctionality = ProfileFunctionality.get(getActivity());
        mPlayer = profileFunctionality.getPlayer(Long.parseLong(Profile.getCurrentProfile().getId()));
        if (mPlayer == null){
            mPlayer = new Player(Long.parseLong(Profile.getCurrentProfile().getId()));
        }
        setHasOptionsMenu(true);

        mClient = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API).build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_edit_profile, container, false);

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mCancelButton =(Button) v.findViewById(R.id.cancel_button);
        mName = (TextView) v.findViewById(R.id.player_name);
        mEmail = (TextView) v.findViewById(R.id.player_email);
        genderSpinner = (Spinner) v.findViewById(R.id.gender_spinner);


        ArrayAdapter<CharSequence> genAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.gender_array,
                android.R.layout.simple_spinner_item);
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mGender = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
                // NOTHING
            }
        });



        mName.setText(mPlayer.first_name);
        mEmail.setText(mPlayer.email);



        mCancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent cancel = new Intent(getActivity(), ViewProfile.class);
                startActivity(cancel);
            }

        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean fieldsFilled = false;
                Intent backToViewProfile = new Intent(getActivity(), ViewProfile.class);

                if (mName.toString().length() == 0) {
                    Toast.makeText(getContext(), "Please provide a name", Toast.LENGTH_SHORT).show();
                    fieldsFilled = true;
                } else {
                    mPlayer.setFirst_name(mName.toString());
                    fieldsFilled = false;
                }

                if (mEmail.toString().length() == 0) {
                    Toast.makeText(getContext(), "Please provide an email address.", Toast.LENGTH_SHORT).show();
                    fieldsFilled = false;

                } else {
                    mPlayer.setEmail(mEmail.toString());
                    fieldsFilled = true;
                }

                if (genderSpinner.getSelectedItemId() != 0) {
                    mPlayer.setGender(mGender);
                    fieldsFilled = true;
                }
                else{
                    Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
                    fieldsFilled = false;
                }

                if (fieldsFilled) {
                    playerDBHelper.updatePlayer(mPlayer);
                    startActivity(backToViewProfile);
                }
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 1: {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    Toast.makeText(getActivity(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }

}
