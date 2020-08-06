package com.gloriakim.devoted;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        //section navigator
        InstructionSectionsPagerAdapter sectionsPagerAdapter = new InstructionSectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.instruction_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
    }
}