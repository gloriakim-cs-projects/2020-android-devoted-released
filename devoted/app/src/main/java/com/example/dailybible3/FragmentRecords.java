package com.example.dailybible3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentRecords extends Fragment {
    private View view;
    public static int number_completion = 0;
    private TextView number_completion_text;
    private ImageView icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10, icon11, icon12, icon13, icon14, icon15;

    public static FragmentRecords newInstance() {
        FragmentRecords fragmentRecords = new FragmentRecords();
        return fragmentRecords;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_records, container, false);

        //show the number of completion
        number_completion_text = (TextView) view.findViewById(R.id.subtitle3);
        if (number_completion == 1 || number_completion == 0) {
            number_completion_text.setText(number_completion + " Time");
        }
        else {
            number_completion_text.setText(number_completion + " Times");
        }

        //show the icons based on the number of completion
        icon1 = (ImageView) view.findViewById(R.id.bible_image1);
        icon2 = (ImageView) view.findViewById(R.id.bible_image2);
        icon3 = (ImageView) view.findViewById(R.id.bible_image3);
        icon4 = (ImageView) view.findViewById(R.id.bible_image4);
        icon5 = (ImageView) view.findViewById(R.id.bible_image5);
        icon6 = (ImageView) view.findViewById(R.id.bible_image6);
        icon7 = (ImageView) view.findViewById(R.id.bible_image7);
        icon8 = (ImageView) view.findViewById(R.id.bible_image8);
        icon9 = (ImageView) view.findViewById(R.id.bible_image9);
        icon10 = (ImageView) view.findViewById(R.id.bible_image10);
        icon11 = (ImageView) view.findViewById(R.id.bible_image11);
        icon12 = (ImageView) view.findViewById(R.id.bible_image12);
        icon13 = (ImageView) view.findViewById(R.id.bible_image13);
        icon14 = (ImageView) view.findViewById(R.id.bible_image14);
        icon15 = (ImageView) view.findViewById(R.id.bible_image15);

        switch(number_completion) {
            case 1:
                icon2.setVisibility(View.INVISIBLE);
                icon3.setVisibility(View.INVISIBLE);
                icon4.setVisibility(View.INVISIBLE);
                icon5.setVisibility(View.INVISIBLE);
                icon6.setVisibility(View.INVISIBLE);
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 2:
                icon3.setVisibility(View.INVISIBLE);
                icon4.setVisibility(View.INVISIBLE);
                icon5.setVisibility(View.INVISIBLE);
                icon6.setVisibility(View.INVISIBLE);
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 3:
                icon4.setVisibility(View.INVISIBLE);
                icon5.setVisibility(View.INVISIBLE);
                icon6.setVisibility(View.INVISIBLE);
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 4:
                icon5.setVisibility(View.INVISIBLE);
                icon6.setVisibility(View.INVISIBLE);
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 5:
                icon6.setVisibility(View.INVISIBLE);
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 6:
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 7:
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 8:
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 9:
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 10:
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 11:
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 12:
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 13:
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
            case 14:
                icon15.setVisibility(View.INVISIBLE);
                break;
            default:
                icon1.setVisibility(View.INVISIBLE);
                icon2.setVisibility(View.INVISIBLE);
                icon3.setVisibility(View.INVISIBLE);
                icon4.setVisibility(View.INVISIBLE);
                icon5.setVisibility(View.INVISIBLE);
                icon6.setVisibility(View.INVISIBLE);
                icon7.setVisibility(View.INVISIBLE);
                icon8.setVisibility(View.INVISIBLE);
                icon9.setVisibility(View.INVISIBLE);
                icon10.setVisibility(View.INVISIBLE);
                icon11.setVisibility(View.INVISIBLE);
                icon12.setVisibility(View.INVISIBLE);
                icon13.setVisibility(View.INVISIBLE);
                icon14.setVisibility(View.INVISIBLE);
                icon15.setVisibility(View.INVISIBLE);
                break;
        }

        return view;
    }
}
