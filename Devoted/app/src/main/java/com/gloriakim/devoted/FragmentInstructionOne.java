package com.gloriakim.devoted;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragmentInstructionOne extends Fragment {
    private View view;

    public static FragmentInstructionOne newInstance() {
        FragmentInstructionOne fragmentInstructionOne = new FragmentInstructionOne();
        return fragmentInstructionOne;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_instruction_one, container, false);

        return view;
    }
}
