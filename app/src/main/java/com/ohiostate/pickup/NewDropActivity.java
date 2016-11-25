package com.ohiostate.pickup;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class NewDropActivity extends AppCompatActivity {

    private static final int REQUEST_ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drop);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new NewDropActivityFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode, this, REQUEST_ERROR, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    // leave if services are unavailable
                    finish();
                }
            });
            errorDialog.show();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_new_drop);

    }
}