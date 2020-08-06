package com.gloriakim.devoted;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity {

    private Button btn_save;
    private CheckBox english, korean, large_font, small_font;
    //make textview clickable for easier selection of reading plan
    private TextView large, small;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //connect variables
        btn_save = (Button) findViewById(R.id.save_button);
        english = (CheckBox) findViewById(R.id.english_button);
        korean = (CheckBox) findViewById(R.id.korean_button);
        large_font = (CheckBox) findViewById(R.id.large_button);
        small_font = (CheckBox) findViewById(R.id.small_button);

        large = (TextView) findViewById(R.id.large);
        small = (TextView) findViewById(R.id.small);

        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                large_font.setChecked(true);
                small_font.setChecked(false);
            }
        });
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                large_font.setChecked(false);
                small_font.setChecked(true);
            }
        });

        //save the settings even after the app is closed
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        final SharedPreferences.Editor editor = settings.edit();

        String font_size = settings.getString("font_size", null);
        String language = settings.getString("language", null);

        //set english
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                english.setChecked(true);
                korean.setChecked(false);
            }
        });

        //set korean
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                english.setChecked(false);
                korean.setChecked(true);
            }
        });

        //set large font
        large_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                large_font.setChecked(true);
                small_font.setChecked(false);
            }
        });

        //set small font
        small_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                large_font.setChecked(false);
                small_font.setChecked(true);
            }
        });

        //check the mark
        if (font_size != null && language != null) {
            /* font_size */
            if (font_size.equals("large_font")) {
                large_font.setChecked(true);
                small_font.setChecked(false);
            }
            else if (font_size.equals("small_font")) {
                large_font.setChecked(false);
                small_font.setChecked(true);
            }
            /* language */
            if (language.equals("english")) {
                english.setChecked(true);
                korean.setChecked(false);
            }
            else if (language.equals("korean")) {
                english.setChecked(false);
                korean.setChecked(true);
            }
        }

        //save button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*font size*/
                if (small_font.isChecked()) {
                    adjustFontSize(getResources().getConfiguration(), (float) 1);
                    editor.putString("font_size", "small_font");
                }
                else if (large_font.isChecked()) {
                    adjustFontSize(getResources().getConfiguration(), (float) 1.2);
                    editor.putString("font_size", "large_font");
                }
                /*language*/
                if (english.isChecked()) {
                    //change the langauge
                    editor.putString("language", "english");
                }
                else if (korean.isChecked()) {
                    //change the language
                    editor.putString("language", "korean");
                }
                editor.commit();

                //FIXME: To update the font right away, it is better to use FragmentManager
                finish();
            }
        });

    }

    private void adjustFontSize(Configuration configuration, float scale) {
        configuration.fontScale = scale;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
