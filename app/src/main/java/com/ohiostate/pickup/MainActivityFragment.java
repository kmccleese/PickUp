package com.ohiostate.pickup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";
    private Button mSearchButton;
    private Button mProfileButton;
    private Button mViewDropButton;
    private Button mNewDropButton;
    private RecyclerView mDropRecyclerView;
    private DropAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "oncreateView() called");
        View v = inflater.inflate(R.layout.activity_main, container, false);

        mProfileButton = (Button) v.findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Start activity
            }
        });
        mSearchButton = (Button) v.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Start activity

            }
        });

        mViewDropButton = (Button) v.findViewById(R.id.view_drop_button);
        mViewDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start ViewDropActivityFragment
            }
        });

        mNewDropButton = (Button) v.findViewById(R.id.new_drop_button);
        mNewDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start NewDropActivity
            }
        });

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
