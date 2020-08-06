package com.gloriakim.devoted;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class FragmentAlarm extends Fragment {
    private View view;
    private CheckBox order_history, order_bible, ninety_days;
    private Button btn_save;
    //set time variables
    private TimePicker time_picker;
    private int hour, minute;
    private String am_pm;
    //show reading plan info
    private ImageButton btn_history, btn_bible, btn_ninety_days;
    //make textview clickable for easier selection of reading plan
    private TextView text_history, text_bible, text_ninety_days;

    public static FragmentAlarm newInstance() {
        FragmentAlarm fragmentAlarm = new FragmentAlarm();
        return fragmentAlarm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarm, container, false);

        //find CheckBox
        ninety_days = (CheckBox) view.findViewById(R.id.ninety_days_box);
        order_bible = (CheckBox) view.findViewById(R.id.book_order_box);
        order_history = (CheckBox) view.findViewById(R.id.history_order_box);

        //set textview clickable for easier selection of reading plan
        text_history = (TextView) view.findViewById(R.id.history_order);
        text_bible = (TextView) view.findViewById(R.id.book_order);
        text_ninety_days = (TextView) view.findViewById(R.id.ninety_days);

        text_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order_history.isChecked()) {
                    order_history.setChecked(false);
                    ninety_days.setChecked(false);
                    order_bible.setChecked(false);
                }
                else {
                    order_history.setChecked(true);
                    ninety_days.setChecked(false);
                    order_bible.setChecked(false);
                }
            }
        });
        text_bible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order_bible.isChecked()) {

                    order_bible.setChecked(false);
                    ninety_days.setChecked(false);
                    order_history.setChecked(false);
                }
                else {
                    order_bible.setChecked(true);
                    ninety_days.setChecked(false);
                    order_history.setChecked(false);
                }
            }
        });
        text_ninety_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ninety_days.isChecked()) {
                    ninety_days.setChecked(false);
                    order_bible.setChecked(false);
                    order_history.setChecked(false);
                }
                else {
                    ninety_days.setChecked(true);
                    order_bible.setChecked(false);
                    order_history.setChecked(false);
                }
            }
        });

        //set reading plan info
        btn_ninety_days = (ImageButton) view.findViewById(R.id.ninety_days_info_icon);
        btn_bible = (ImageButton) view.findViewById(R.id.book_order_info_icon);
        btn_history = (ImageButton) view.findViewById(R.id.history_order_info_icon);

        //set each reading plan's information
        btn_ninety_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), FragmentAlarmPlanInfo.class);
                intent.putExtra("reading_plan", "ninety_days");
                getActivity().startActivity(intent);
            }
        });
        btn_bible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), FragmentAlarmPlanInfo.class);
                intent.putExtra("reading_plan", "bible");
                getActivity().startActivity(intent);
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), FragmentAlarmPlanInfo.class);
                intent.putExtra("reading_plan", "history");
                getActivity().startActivity(intent);
            }
        });

        //for setting THE CONTROLLER VARIABLE reading_day
        final SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
        final SharedPreferences.Editor editor = settings.edit();

        //save button; set the alarm notification
        btn_save = (Button) view.findViewById(R.id.save_button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ninety_days.isChecked() == false && order_bible.isChecked() == false && order_history.isChecked() == false) {
                    toastMessage("Please mark one of reading plan.");
                }
                else {

                    //reset THE CONTROLLER VARIABLE reading_day
                    editor.putInt("reading_day", 1);
                    editor.commit();

                    //set reading plan
                    if (ninety_days.isChecked()) {
                        editor.putString("reading_plan", "ninety_days");
                    }
                    else if (order_bible.isChecked()) {
                        editor.putString("reading_plan", "order_bible");
                    }
                    else if (order_history.isChecked()) {
                        editor.putString("reading_plan", "order_history");
                    }
                    editor.commit();

                    Calendar calendar = Calendar.getInstance();
                    alarmTime(calendar);

                    Intent intent = new Intent(getActivity().getApplicationContext(), AlertReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);

                    toastMessage("Alarm is set!");
//                toastMessage("Alarm is set at "+ hour +":"+ minute+" "+am_pm);
                }
            }
        });

        return view;
    }

    private void alarmTime(Calendar calendar) {
        //find time picker
        time_picker = (TimePicker) view.findViewById(R.id.time_picker);
        //set hour and minute
        if (Build.VERSION.SDK_INT >= 23 ){
            hour = time_picker.getHour();
            minute = time_picker.getMinute();
        }
        else{
            hour = time_picker.getCurrentHour();
            minute = time_picker.getCurrentMinute();
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

//        //mark am or pm
//        if(hour > 12) {
//            am_pm = "PM";
//            hour = hour - 12;
//        }
//        else {
//            am_pm="AM";
//        }
    }

    private void readingPlan() {
        final SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);

        //allow to check only one of options
        ninety_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ninety_days.isChecked()) {
                    order_bible.setChecked(false);
                    order_history.setChecked(false);
                }
            }
        });
        order_bible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check only one of public or private check-marks
                if (order_bible.isChecked()) {
                    ninety_days.setChecked(false);
                    order_history.setChecked(false);
                }
            }
        });
        order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check only one of public or private check-marks
                if (order_history.isChecked()) {
                    ninety_days.setChecked(false);
                    order_bible.setChecked(false);
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
