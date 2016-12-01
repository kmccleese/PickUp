package com.ohiostate.pickup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class ViewDropActivity extends AppCompatActivity {

    public static final String EXTRA_DROP_ID = "com.ohiostate.pickup.drop_id";
    public static final String TAG = "ViewDropActivity";

    public static Intent newIntent(Context packageContext, int dropId) {
        Intent intent = new Intent(packageContext, ViewDropActivity.class);
        intent.putExtra(EXTRA_DROP_ID, dropId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drop);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    protected Fragment createFragment() {
        int dropID = (int) getIntent().getSerializableExtra(EXTRA_DROP_ID);
        return ViewDropActivityFragment.newInstance(dropID);
    }

}
