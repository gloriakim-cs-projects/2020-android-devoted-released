package com.example.dailybible3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class FragmentAlarmPlanInfo extends AppCompatActivity {

    private TextView title, text;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_plan_info);

        //connect variables
        title = (TextView) findViewById(R.id.title);
        text = (TextView) findViewById(R.id.text);
        btn_close = (Button) findViewById(R.id.close_button);

        //close the screen
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //grab the passed reading_plan variable and show the relative contents
        Intent intent = getIntent();
        String reading_plan = intent.getStringExtra("reading_plan");

        if (reading_plan.equals("ninety_days")) {
            title.setText("The 90-Day Challenge");
            text.setText("The 90-Day Challenge offers you to a competitive reading plan from beginning (Genesis) to end (Book of Revelation) in 90 days, giving about 15 chapters per day.");
        }
        else if (reading_plan.equals("bible")) {
            title.setText("The Bible Order Plan");
            text.setText("The Bible Order reading plan directs you to read the Bible from beginning (Genesis) to end (Book of Revelation) in one year, giving about 3-4 chapters per day.");
        }
        else if (reading_plan.equals("history")) {
            title.setText("The History Order Plan");
            text.setText("The History Order reading plan directs you to read the Bible in a way that events happened in one year, giving about 3-4 chapters per day.");
        }
        else {
            title.setText("ERROR");
            text.setText("There is no selected reading plan to explain.");
        }
    }
}
