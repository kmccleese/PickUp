package com.ohiostate.pickup;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewDropActivityFragment extends Fragment {

    private static final String TAG = "NewDropActivity";
    private Button mDropButton;
    private Button mShareButton;
    private Button mCancelButton;
    private Button mDateButton;
    private EditText mMessageEditText;
    private TextView mDateText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "NewDropActivity onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_new_drop, container, false);

        // instantiate
        mDropButton = (Button) v.findViewById(R.id.drop_button);
        mShareButton = (Button) v.findViewById(R.id.share_button);
        mCancelButton = (Button) v.findViewById(R.id.cancel_button);
        mDateButton = (Button) v.findViewById(R.id.new_drop_date_button);
        mMessageEditText = (EditText) v.findViewById(R.id.message_edit_text);
        mDateText = (TextView) v.findViewById(R.id.new_drop_date_text);

        // listeners
        mDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create the drop
            }
        });
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share drop to Facebook
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel the creation of this drop
            }
        });
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open calendar and set the date
                // mDateText.setText("When: " + mDrop.getDate().toString());
            }
        });
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // set message to new text
                // mDrop.setMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }
        });

        return v;
    }
}
