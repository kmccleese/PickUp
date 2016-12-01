package com.ohiostate.pickup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ViewProfile extends AppCompatActivity {
    public static final String EXTRA_PROFILE_ID = "com.ohiostate.pickup.profile_id";

    public static Intent newIntent(Context packageContext, long profileId) {
        Intent intent = new Intent(packageContext, ViewProfile.class);
        intent.putExtra(EXTRA_PROFILE_ID, profileId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    protected Fragment createFragment() {
        long profileID = (long) getIntent().getSerializableExtra(EXTRA_PROFILE_ID);
        return ViewProfileFragment.newInstance(profileID);
    }

}

