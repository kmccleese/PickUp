package com.ohiostate.pickup;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewDropActivityFragment extends Fragment {

    private static final String TAG = "NewDropActivity";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private Button mDropButton;
    private Button mShareButton;
    private Button mCancelButton;
    private Button mDateButton;
    private EditText mMessageEditText;
    private TextView mDateTextView;
    private TextView mTimeTextView;
    private Button mTimeButton;
    private Date mDate;
    private String mMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "NewDropActivity onCreate() called");
        setHasOptionsMenu(true);
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
        mDateTextView = (TextView) v.findViewById(R.id.new_drop_date_text);
        mTimeButton = (Button) v.findViewById(R.id.new_drop_time_button);
        mTimeTextView = (TextView) v.findViewById(R.id.new_drop_time_text);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDate = new GregorianCalendar(year, month, day).getTime();

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
            updateDate();
        }

        if(requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_DATE);
            mDate = date;
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
}
