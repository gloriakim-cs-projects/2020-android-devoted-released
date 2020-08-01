package com.example.dailybible3;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailybible3.ui.main.SectionsPagerAdapter;

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