package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gm.safedrive.R;
import com.gm.safedrive.application.Current;
import com.gm.safedrive.banks.VehicleBank;
import com.gm.safedrive.controllers.fragments.dialogs.DatePickerFragment;
import com.gm.safedrive.controllers.fragments.dialogs.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OperationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Current mCurrent;
    private static final String TAG = "OperationActivityr";
    private ImageButton mClose;
    private ImageButton mSave;
    private TextView mSeekbarValueOutput;
    private TextView mSeekbarValuePercent;
    private SeekBar mSeekBar;
    private EditText mDate;
    private EditText mTime;
    private EditText mOdometerValue;
    private EditText mLitersInput;
    private EditText mPriceInput;
    private EditText mPricePerLInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        mCurrent = new Current();

        mClose = findViewById(R.id.activity_operation_close_btn);
        mSave = findViewById(R.id.activity_operation_save_btn);
        mDate = findViewById(R.id.activity_operation_date_input);
        mTime = findViewById(R.id.activity_operation_time_input);
        mOdometerValue = findViewById(R.id.activity_operation_odometer_value_input);
        mSeekbarValueOutput = findViewById(R.id.activity_operation_remaining_fuel_text);
        mSeekbarValuePercent = findViewById(R.id.activity_operation_remaining_fuel_percent);
        mSeekBar = findViewById(R.id.activity_operation_remaining_fuel_input);
        mLitersInput = findViewById(R.id.activity_operation_l_input);
        mPriceInput = findViewById(R.id.activity_operation_price_input);
        mPricePerLInput = findViewById(R.id.activity_operation_price_per_l);

        initActivity();
    }

    private void initActivity(){
        Calendar c = Calendar.getInstance();
        mDate.setText(DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime()));
        mTime.setText(mCurrent.getTimeToString());
        if(VehicleBank.LOADED_VEHICLE != null){
            mOdometerValue.setText(String.format("%.0f", VehicleBank.LOADED_VEHICLE.getDistanceCovered()));
        }

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date picker");
            }
        });
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Time picker");
            }
        });

        mSeekBar.setProgress(50);
        mSeekbarValueOutput.setText(String.format(getResources().getString(R.string.val_liters), (float)(VehicleBank.LOADED_VEHICLE.getTankCapacity()/2)));
        mSeekbarValuePercent.setText(String.format(getResources().getString(R.string.val_percent_parenthesis), mSeekBar.getProgress()));
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float currLiters = ((float) progress / 100) * (float) VehicleBank.LOADED_VEHICLE.getTankCapacity();
                mSeekbarValueOutput.setText(String.format(getResources().getString(R.string.val_liters), currLiters));
                mSeekbarValuePercent.setText(String.format(getResources().getString(R.string.val_percent_parenthesis), progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        initRefill();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        mDate.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String currentTimeString = hourOfDay + ":" + minute;
        mTime.setText(currentTimeString);
    }

    public void initRefill(){
        // PRIX TOTAL
        mPriceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(!Current.stringIsNullOrWhitespace(s.toString(), mLitersInput.getText().toString(), mPricePerLInput.getText().toString())){
                    final double liters = Double.parseDouble(mLitersInput.getText().toString()), price = Double.parseDouble(s.toString());
                    if(liters > 0){
                        mPricePerLInput.setText(String.format(getResources().getString(R.string.val_double), price / liters));
                    }
                }
            }
        });

        // NOMBRE DE LITRES
        mLitersInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(!Current.stringIsNullOrWhitespace(s.toString(), mPriceInput.getText().toString(), mPricePerLInput.getText().toString())){
                    final double liters = Double.parseDouble(s.toString()), price = Double.parseDouble(mPriceInput.getText().toString());
                    if(liters > 0 && price > 0){
                        Log.d(TAG, "Editable is higher than zero");
                        mPricePerLInput.setText(String.format(getResources().getString(R.string.val_double), price / liters));
                    }
                }
            }
        });
    }
}
