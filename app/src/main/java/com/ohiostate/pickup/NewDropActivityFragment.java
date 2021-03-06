package com.ohiostate.pickup;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class NewDropActivityFragment extends Fragment {
    private DropDatabaseHelper dh;
    private Drop drop;
    private static final String TAG = "NewDropActivity";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private Button mDropButton;
    private Button mCancelButton;
    private Button mDateButton;
    private EditText mMessageEditText;
    private Button mLocation;
    private TextView mLocationField;
    private TextView mDateTextView;
    private TextView mTimeTextView;
    private Button mTimeButton;
    private Date mDate;
    private String mMessage;
    private String mGender;
    private Spinner genderSpinner;
    private String mDifficulty;
    private Spinner difficultySpinner;
    private EditText mNumPlayersEditText;
    private Integer mNumPlayers;
    private Spinner sportSpinner;
    private String mSport;
    private Button logoutButton;
    private GoogleApiClient mClient;
    private Button locationButton;
    private String location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mClient = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API).build();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_new_drop, container, false);

        // instantiate
        drop = new Drop();
        mDropButton = (Button) v.findViewById(R.id.drop_button);
        mCancelButton = (Button) v.findViewById(R.id.cancel_button);
        mDateButton = (Button) v.findViewById(R.id.new_drop_date_button);
        mMessageEditText = (EditText) v.findViewById(R.id.message_edit_text);
        mNumPlayersEditText = (EditText) v.findViewById(R.id.num_players_text);
        mDateTextView = (TextView) v.findViewById(R.id.new_drop_date_text);
        mTimeButton = (Button) v.findViewById(R.id.new_drop_time_button);
        mTimeTextView = (TextView) v.findViewById(R.id.new_drop_time_text);
        mLocation = (Button) v.findViewById(R.id.location_button);
        mLocationField = (TextView) v.findViewById(R.id.location);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDate = new GregorianCalendar(year, month, day).getTime();

        sportSpinner = (Spinner) v.findViewById(R.id.sport_spinner);
        ArrayAdapter<CharSequence> sportAdapater = ArrayAdapter.createFromResource(this.getActivity(), R.array.sport_array,
                android.R.layout.simple_spinner_item);
        sportAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(sportAdapater);
        if(drop.getSport() != null) {
            sportSpinner.setPrompt(drop.getSport());
        }
        sportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mSport = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
                //NOTHING
            }
        });


        difficultySpinner = (Spinner) v.findViewById(R.id.difficulty_spinner);
        ArrayAdapter<CharSequence> diffAdapater = ArrayAdapter.createFromResource(this.getActivity(), R.array.difficulty_array,
                android.R.layout.simple_spinner_item);
        diffAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(diffAdapater);
        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mDifficulty = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
                //NOTHING
            }
        });

        genderSpinner = (Spinner) v.findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapater = ArrayAdapter.createFromResource(this.getActivity(), R.array.gender_array,
                android.R.layout.simple_spinner_item);
        adapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapater);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mGender = parent.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
                //NOTHING
            }
        });

        // listeners
        mDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sportSpinner.getSelectedItemId() != 0) {
                    drop.setSport(mSport);

                    if(difficultySpinner.getSelectedItemId() != 0) {
                        drop.setDifficulty(mDifficulty);

                        if(drop.getDate() != null) {
                            // date gets set in onActivityResult

                            if(drop.getPlay_time() != null) {
                                // time gets set in onActivityResult

                                if(mNumPlayers != null) {
                                    drop.setNum_players(mNumPlayers);

                                    if(genderSpinner.getSelectedItemId() != 0) {
                                        drop.setGender(mGender);

                                        if (location != null) {
                                            drop.setLocation(location);

                                            if (mMessage != null) {
                                                drop.setMessage(mMessage);

                                                DropFunctionality.get(getActivity()).addDrop(drop);
                                                Intent dropped = new Intent(getActivity(), MainActivity.class);
                                                startActivity(dropped);
                                            } else {
                                                Toast.makeText(getContext(), "Please select a date", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Toast.makeText(getContext(), "Please enter location", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                            Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
                                        }
                                } else {
                                    Toast.makeText(getContext(), "Please enter number of players", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getContext(), "Please select a date", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getContext(), "Please select a date", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "Please select a skill level", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please select a sport", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getContext(), "Drop " + drop.getId() + " created", Toast.LENGTH_SHORT).show();

            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel the creation of this drop
                Intent cancel = new Intent(getActivity(), MainActivity.class);
                startActivity(cancel);
            }

        });


        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);
                dialog.setTargetFragment(NewDropActivityFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mDate);
                dialog.setTargetFragment(NewDropActivityFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMessage = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }

        });



        mNumPlayersEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    mNumPlayers = Integer.parseInt(s.toString());
                }
                catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gps = new GPSTracker(getActivity());
                MapsActivity map = new MapsActivity();

                // check if GPS enabled
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if(addresses.size() != 0) {
                            location = addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getAddressLine(1);
                            mLocationField.setText(location);
                            drop.setLocation(location);
                        Log.d("Your Location is: ", geocoder.getFromLocation(latitude, longitude, 1).toString());
                        } else {
                            mLocationField.setText("App does not have permission for location");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // \n is for new line

                }else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mClient.disconnect();
    }

    @Override
    public void onStop() {
        super.onStop();

        mClient.disconnect();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult()");

        if(requestCode == REQUEST_DATE) {
            Log.d(TAG, "inside if");
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mDate = date;
            drop.setDate(date);
            updateDate();
        }

        if(requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_DATE);
            mDate = date;
            drop.setPlay_time(date);
            updateTime();
        }
    }

    private void updateDate() {
        Log.d(TAG, "updateDate()");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        String date = " " + dateFormatter.format(mDate);
        mDateTextView.setText(date);
    }

    private void updateTime() {
        DateFormat dateFormat = DateFormat.getTimeInstance();
        String time = " " + dateFormat.format(mDate);
        mTimeTextView.setText(time);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
