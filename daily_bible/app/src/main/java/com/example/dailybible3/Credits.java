package com.example.dailybible3;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailybible3.R;

public class Credits extends AppCompatActivity {

    private TextView link_alarm, link_bible, link_note, link_record, link_photo, link_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //hyperlinks
        link_alarm = (TextView) findViewById(R.id.credit_alarm);
        link_bible = (TextView) findViewById(R.id.credit_bible);
        link_note = (TextView) findViewById(R.id.credit_note);
        link_record = (TextView) findViewById(R.id.credit_record);
        link_photo = (TextView) findViewById(R.id.link_photo);
        link_font = (TextView) findViewById(R.id.link_font);

        link_alarm.setMovementMethod(LinkMovementMethod.getInstance());
        link_bible.setMovementMethod(LinkMovementMethod.getInstance());
        link_note.setMovementMethod(LinkMovementMethod.getInstance());
        link_record.setMovementMethod(LinkMovementMethod.getInstance());
        link_photo.setMovementMethod(LinkMovementMethod.getInstance());
        link_font.setMovementMethod(LinkMovementMethod.getInstance());

        //close button
        Button btn_close = (Button) findViewById(R.id.close_button);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}