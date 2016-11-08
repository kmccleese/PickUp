package com.ohiostate.pickup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";
    private RecyclerView mDropRecyclerView;
    private DropAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "oncreateView() called");
        View v = inflater.inflate(R.layout.activity_main, container, false);

        mDropRecyclerView = (RecyclerView) v.findViewById(R.id.drop_recycler_view);
        mDropRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
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

    private void updateUI() {
        // get a list of drops from the database
        DropFunctionality dropFunctionality = DropFunctionality.get(getActivity());
        List<Drop> drops = dropFunctionality.getDrops();

        if(mAdapter == null) {
            mAdapter = new DropAdapter(drops);
            mDropRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDrops(drops);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class DropHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mMessageTextView;
        private TextView mSportTextView;
        private TextView mDateTextView;
        private Drop mDrop;

        public DropHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.name_text);
            mMessageTextView = (TextView) itemView.findViewById(R.id.drop_message_text);
            mSportTextView = (TextView) itemView.findViewById(R.id.drop_sport_text);
            mDateTextView = (TextView) itemView.findViewById(R.id.drop_date_text);
        }

        // get drop info from database
        public void bindDrop(Drop drop) {
            mDrop = drop;
            mNameTextView.setText(Integer.toString(mDrop.getId()));
            mMessageTextView.setText(mDrop.getMessage());
            mSportTextView.setText(mDrop.getSport());
            SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
            String date = " " + dateFormatter.format(mDrop.getDate());
            DateFormat dateFormat = DateFormat.getTimeInstance();
            String time = " " + dateFormat.format(mDrop.getPlay_time());
            mDateTextView.setText(date + time);
        }

        @Override
        public void onClick(View v) {
            // start ViewDropActivity
            Intent intent = ViewDropActivity.newIntent(getActivity(), mDrop.getId());
            startActivity(intent);
        }
    }

    private class DropAdapter extends RecyclerView.Adapter<DropHolder> {

        private List<Drop> mDrops;

        public DropAdapter(List<Drop> drops) {
            mDrops = drops;
        }

        @Override
        public DropHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.feed_item_drop, parent, false);
            return new DropHolder(view);
        }

        @Override
        public void onBindViewHolder(DropHolder holder, int position) {
            Drop drop = mDrops.get(position);
            holder.bindDrop(drop);
        }

        @Override
        public int getItemCount() {
            return mDrops.size();
        }

        public void setDrops(List<Drop> drops) {
            mDrops = drops;
        }
    }
}
