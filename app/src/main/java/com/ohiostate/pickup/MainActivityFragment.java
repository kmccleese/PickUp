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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        // get a list of drops from the database
        List<String> drops = Arrays.asList("one", "two", "three");

        if(mAdapter == null) {
            mAdapter = new DropAdapter(drops);
            mDropRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class DropHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mMessageTextView;
        private TextView mSportTextView;
        private TextView mDateTextView;
        private String mDrop;

        public DropHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.name_text);
            mMessageTextView = (TextView) itemView.findViewById(R.id.drop_message_text);
            mSportTextView = (TextView) itemView.findViewById(R.id.drop_sport_text);
            mDateTextView = (TextView) itemView.findViewById(R.id.drop_date_text);
        }

        // get drop info from database
        public void bindDrop(String drop) {
            mDrop = drop;
            mNameTextView.setText(mDrop);
            mMessageTextView.setText(mDrop + " message");
            mSportTextView.setText(mDrop + " sport");
            mDateTextView.setText(mDrop + " date");
        }

        @Override
        public void onClick(View v) {
            // start ViewDropActivity
            Intent intent = ViewDropActivity.newIntent(getActivity(), mDrop);
            startActivity(intent);
        }
    }

    private class DropAdapter extends RecyclerView.Adapter<DropHolder> {

        private List<String> mDrops;

        public DropAdapter(List<String> drops) {
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
            String drop = mDrops.get(position);
            holder.bindDrop(drop);
        }

        @Override
        public int getItemCount() {
            return mDrops.size();
        }
    }
}
