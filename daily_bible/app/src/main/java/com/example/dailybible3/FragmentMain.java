package com.example.dailybible3;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.WINDOW_SERVICE;

public class FragmentMain extends Fragment {
    private View view;
    private TextView date_text, verse_text, day_text;
    private Button btn_setting, btn_credit;
    private CheckBox complete_box;
    private RelativeLayout complete_layout;
    private TextView complete_button_background;

    //Rule: separate names and chapters with \n unless there are THREE. remove semicolon
    private String[] ninety = {
            //<editor-fold desc="ninety reading plan"
            "Genesis\n1-14",
            "Genesis\n15-28",
            "Genesis\n29-42",
            "Genesis\n43-50\nExodus\n1-6",
            "Exodus\n7-20",
            "Exodus\n21-34",
            "Exodus\n35-40\nLeviticus\n1-8",
            "Leviticus\n9-22",
            "Leviticus\n23-27\nNumbers\n1-9",
            "Numbers\n10-23",
            "Numbers\n24-36\nDeuteronomy\n1",
            "Deuteronomy\n2-15",
            "Deuteronomy\n16-29",
            "Deuteronomy\n30-34\nJoshua\n1-9",
            "Joshua\n10-23",
            "Joshua\n24\nJudges\n1-13",
            "Judges\n14-21\nRuth 1-4\n1 Samuel 1-2",
            "1 Samuel\n3-16",
            "1 Samuel\n17-30",
            "1 Samuel\n31\n2 Samuel\n1-12",
            "2 Samuel\n13-24\n1 Kings\n1",
            "1 Kings\n2-14",
            "1 Kings\n15-22\n2 Kings\n1-5",
            "2 Kings\n6-18",
            "2 Kings\n19-25\n1 Chronicles\n1-6",
            "1 Chronicles\n7-19",
            "1 Chronicles\n20-29\n2 Chronicles\n1-3",
            "2 Chronicles\n4-16",
            "2 Chronicles\n17-29",
            "2 Chronicles\n30-36\nEzra\n1-6",
            "Ezra\n7-10\nNehemiah\n1-9",
            "Nehemiah\n10-13\nEsther\n1-9",
            "Esther\n10\nJob\n1-12",
            "Job\n13-25",
            "Job\n26-38",
            "Job\n39-42\nPsalms\n1-9",
            "Psalms\n10-22",
            "Psalms\n23-35",
            "Psalms\n36-48",
            "Psalms\n49-61",
            "Psalms\n62-74",
            "Psalms\n75-87",
            "Psalms\n88-100",
            "Psalms\n101-113",
            "Psalms\n114-126",
            "Psalms\n127-139",
            "Psalms\n140-150\nProverbs\n1-2",
            "Proverbs\n3-15",
            "Proverbs\n16-28",
            "Proverbs\n29-31\nEcclesiastes\n1-10",
            "Ecclesiastes\\n11-12\\nSong of\\nSolomon 1-8\\nIsaiah 1-3",
            "Isaiah\n4-16",
            "Isaiah\n17-29",
            "Isaiah\n30-42",
            "Isaiah\n43-55",
            "Isaiah\n56-66\nJeremiah\n1-2",
            "Jeremiah\n3-15",
            "Jeremiah\n16-28",
            "Jeremiah\n29-41",
            "Jeremiah\n42-52\nLamentations\n1-2",
            "Lamentations\n3-5\nEzekiel\n1-10",
            "Ezekiel\n11-23",
            "Ezekiel\n24-36",
            "Ezekiel\n37-48\nDaniel\n1",
            "Daniel\n2-12\nHosea\n1-2",
            "Hosea\n3-14\nJoel\n1",
            "Joel 2-3\nAmos 1-9\nObadiah 1\nJonah 1",
            "Jonah 2-4\nMicah 1-7\nNahum 1-3",
            "Habakkuk 1-3\nZephaniah 1-3\nHaggai 1-2\nZechariah 1-5",
            "Zechariah\n6-14\nMalachi\n1-4",
            "Matthew\n1-13",
            "Matthew\n14-26",
            "Matthew\n27-28\nMark\n1-11",
            "Mark\n12-16\nLuke\n1-8",
            "Luke\n9-21",
            "Luke\n22-24\nJohn\n1-10",
            "John\n11-21\nActs\n1-2",
            "Acts\n3-15",
            "Acts\n16-28",
            "Romans\n1-13",
            "Romans\n14-16\n1 Corinthians\n1-10",
            "1 Corinthians\n11-16\n2 Corinthians\n1-7",
            "2 Corinthians\n8-13\nGalatians 1-6\nEphesians 1",
            "Ephesians 2-6\nPhilippians 1-4\nColossians 1-4",
            "1 Thes. 1-5\n2 Thes. 1-3\n1 Timothy 1-5",
            "1 Timothy 6\n2 Timothy 1-4\nTitus 1-3\nPhilemon 1\nHebrews 1-4",
            "Hebrews 5-13\nJames 1-4",
            "James 5\n1 Peter 1-5\n2 Peter 1-3\n1 John 1-4",
            "1 John 5\n2 John 1\n3 John 1\nJude 1\nRev. 1-9",
            "Revelation\n10-22"
            //</editor-fold>
    };
    private String[] bible = {
            //<editor-fold desc="bible order reading plan">
            "Genesis\n1-4",
            "Genesis\n5-8",
            "Genesis\n9-12",
            "Genesis\n13-17",
            "Genesis\n18-20",
            "Genesis\n21-23",
            "Genesis\n24-25",
            "Genesis\n26-28",
            "Genesis\n29-31",
            "Genesis\n32-35",
            "Genesis\n36-38",
            "Genesis\n39-41",
            "Genesis\n42-43",
            "Genesis\n44-46",
            "Genesis\n47-50",
            "Exodus\n1-4",
            "Exodus\n5-7",
            "Exodus\n8-10",
            "Exodus\n11-13",
            "Exodus\n14-16",
            "Exodus\n17-20",
            "Exodus\n21-23",
            "Exodus\n24-27",
            "Exodus\n28-30",
            "Exodus\n31-34",
            "Exodus\n35-37",
            "Exodus\n38-40",
            "Leviticus\n1-4",
            "Leviticus\n5-7",
            "Leviticus\n8-10",
            "Leviticus\n11-13",
            "Leviticus\n14-15",
            "Leviticus\n16-18",
            "Leviticus\n19-21",
            "Leviticus\n22-23",
            "Leviticus\n24-25",
            "Leviticus\n26-27",
            "Numbers\n1-2",
            "Numbers\n3-4",
            "Numbers\n5-6",
            "Numbers\n7",
            "Numbers\n8-10",
            "Numbers\n11-13",
            "Numbers\n14-15",
            "Numbers\n16-18",
            "Numbers\n19-21",
            "Numbers\n22-24",
            "Numbers\n25-26",
            "Numbers\n27-29",
            "Numbers\n30-32",
            "Numbers\n33-36",
            "Deuteronomy\n1-2",
            "Deuteronomy\n3-4",
            "Deuteronomy\n5-8",
            "Deuteronomy\n9-11",
            "Deuteronomy\n12-15",
            "Deuteronomy\n16-19",
            "Deuteronomy\n20-22",
            "Deuteronomy\n23-25",
            "Deuteronomy\n26-27",
            "Deuteronomy\n28-29",
            "Deuteronomy\n30-32",
            "Deuteronomy\n33-34",
            "Joshua\n1-4",
            "Joshua\n5-7",
            "Joshua\n8-10",
            "Joshua\n11-13",
            "Joshua\n14-17",
            "Joshua\n18-20",
            "Joshua\n21-22",
            "Joshua\n23-24",
            "Judges\n1-3",
            "Judges\n4-5",
            "Judges\n6-8",
            "Judges\n9-10",
            "Judges\n11-13",
            "Judges\n14-16",
            "Judges\n17-19",
            "Judges\n20-21",
            "Ruth\n1-4",
            "1 Samuel\n1-3",
            "1 Samuel\n4-7",
            "1 Samuel\n8-12",
            "1 Samuel\n13-14",
            "1 Samuel\n15-16",
            "1 Samuel\n17-18",
            "1 Samuel\n19-21",
            "1 Samuel\n22-24",
            "1 Samuel\n25-27",
            "1 Samuel\n28-31",
            "2 Samuel\n1-3",
            "2 Samuel\n4-7",
            "2 Samuel\n8-11",
            "2 Samuel\n12-13",
            "2 Samuel\n14-16",
            "2 Samuel\n17-19",
            "2 Samuel\n20-22",
            "2 Samuel\n23-24",
            "1 Kings\n1-2",
            "1 Kings\n3-5",
            "1 Kings\n6-7",
            "1 Kings\n8-9",
            "1 Kings\n10-12",
            "1 Kings\n13-15",
            "1 Kings\n16-18",
            "1 Kings\n19-20",
            "1 Kings\n21-22",
            "2 Kings\n1-3",
            "2 Kings\n4-5",
            "2 Kings\n6-8",
            "2 Kings\n9-10",
            "2 Kings\n11-13",
            "2 Kings\n14-16",
            "2 Kings\n17-18",
            "2 Kings\n19-21",
            "2 Kings\n22-23",
            "2 Kings\n24-25",
            "1 Chronicles\n1-2",
            "1 Chronicles\n3-4",
            "1 Chronicles\n5-6",
            "1 Chronicles\n7-9",
            "1 Chronicles\n10-12",
            "1 Chronicles\n13-16",
            "1 Chronicles\n17-19",
            "1 Chronicles\n20-23",
            "1 Chronicles\n24-26",
            "1 Chronicles\n27-29",
            "2 Chronicles\n1-4",
            "2 Chronicles\n5-7",
            "2 Chronicles\n8-11",
            "2 Chronicles\n12-16",
            "2 Chronicles\n17-20",
            "2 Chronicles\n21-24",
            "2 Chronicles\n25-28",
            "2 Chronicles\n29-31",
            "2 Chronicles\n32-34",
            "2 Chronicles\n35-36",
            "Ezra\n1-4",
            "Ezra\n5-7",
            "Ezra\n8-10",
            "Nehemiah\n1-3",
            "Nehemiah\n4-7",
            "Nehemiah\n8-10",
            "Nehemiah\n11-13",
            "Esther\n1-5",
            "Esther\n6-10",
            "Job\n1-4",
            "Job\n5-8",
            "Job\n9-12",
            "Job\n13-16",
            "Job\n17-20",
            "Job\n21-24",
            "Job\n25-30",
            "Job\n31-34",
            "Job\n35-38",
            "Job\n39-42",
            "Psalms\n1-8",
            "Psalms\n9-17",
            "Psalms\n18-21",
            "Psalms\n22-27",
            "Psalms\n28-33",
            "Psalms\n34-37",
            "Psalms\n38-42",
            "Psalms\n43-49",
            "Psalms\n50-55",
            "Psalms\n56-61",
            "Psalms\n62-68",
            "Psalms\n69-72",
            "Psalms\n73-77",
            "Psalms\n78-80",
            "Psalms\n81-88",
            "Psalms\n89-94",
            "Psalms\n95-103",
            "Psalms\n104-106",
            "Psalms\n107-111",
            "Psalms\n112-118",
            "Psalm\n119",
            "Psalms\n120-133",
            "Psalms\n134-140",
            "Psalms\n141-150",
            "Proverbs\n1-3",
            "Proverbs\n4-7",
            "Proverbs\n8-11",
            "Proverbs\n12-14",
            "Proverbs\n15-17",
            "Proverbs\n18-20",
            "Proverbs\n21-23",
            "Proverbs\n24-26",
            "Proverbs\n27-29",
            "Proverbs\n30-31",
            "Ecclesiastes\n1-4",
            "Ecclesiastes\n5-8",
            "Ecclesiastes\n9-12",
            "Song of\nSolomon\n1-4",
            "Song of\nSolomon\n5-8",
            "Isaiah\n1-3",
            "Isaiah\n4-8",
            "Isaiah\n9-11",
            "Isaiah\n12-14",
            "Isaiah\n15-19",
            "Isaiah\n20-24",
            "Isaiah\n25-28",
            "Isaiah\n29-31",
            "Isaiah\n32-34",
            "Isaiah\n35-37",
            "Isaiah\n38-40",
            "Isaiah\n41-43",
            "Isaiah\n44-46",
            "Isaiah\n47-49",
            "Isaiah\n50-52",
            "Isaiah\n53-56",
            "Isaiah\n57-59",
            "Isaiah\n60-63",
            "Isaiah\n64-66",
            "Jeremiah\n1-3",
            "Jeremiah\n4-5",
            "Jeremiah\n6-8",
            "Jeremiah\n9-11",
            "Jeremiah\n12-14",
            "Jeremiah\n15-17",
            "Jeremiah\n18-21",
            "Jeremiah\n22-24",
            "Jeremiah\n25-27",
            "Jeremiah\n28-30",
            "Jeremiah\n31-32",
            "Jeremiah\n33-36",
            "Jeremiah\n37-39",
            "Jeremiah\n40-43",
            "Jeremiah\n44-46",
            "Jeremiah\n47-48",
            "Jeremiah\n49",
            "Jeremiah\n50",
            "Jeremiah\n51-52",
            "Lamentations\n1-2",
            "Lamentations\n3-5",
            "Ezekiel\n1-4",
            "Ezekiel\n5-8",
            "Ezekiel\n9-12",
            "Ezekiel\n13-15",
            "Ezekiel\n16-17",
            "Ezekiel\n18-20",
            "Ezekiel\n21-22",
            "Ezekiel\n23-24",
            "Ezekiel\n25-27",
            "Ezekiel\n28-30",
            "Ezekiel\n31-32",
            "Ezekiel\n33-35",
            "Ezekiel\n36-38",
            "Ezekiel\n39-40",
            "Ezekiel\n41-43",
            "Ezekiel\n44-46",
            "Ezekiel\n47-48",
            "Daniel\n1-3",
            "Daniel\n4-5",
            "Daniel\n6-8",
            "Daniel\n9-12",
            "Hosea\n1-4",
            "Hosea\n5-9",
            "Hosea\n10-14",
            "Joel\n1-3",
            "Amos\n1-4",
            "Amos\n5-9",
            "Obadiah\n1",
            "Jonah\n1-4",
            "Micah\n1-4",
            "Micah\n5-7",
            "Nahum\n1-3",
            "Habakkuk\n1-3",
            "Zephaniah\n1-3",
            "Haggai\n1-2",
            "Zechariah\n1-5",
            "Zechariah\n6-10",
            "Zechariah\n11-14",
            "Malachi\n1-4",
            "Matthew\n1-4",
            "Matthew\n5-6",
            "Matthew\n7-9",
            "Matthew\n10-11",
            "Matthew\n12-13",
            "Matthew\n14-17",
            "Matthew\n18-20",
            "Matthew\n21-22",
            "Matthew\n23-24",
            "Matthew\n25-26",
            "Matthew\n27-28",
            "Mark\n1-3",
            "Mark\n4-5",
            "Mark\n6-7",
            "Mark\n8-9",
            "Mark\n10-11",
            "Mark\n12-13",
            "Mark\n14",
            "Mark\n15-16",
            "Luke\n1-2",
            "Luke\n3-4",
            "Luke\n5-6",
            "Luke\n7-8",
            "Luke\n9-10",
            "Luke\n11-12",
            "Luke\n13-15",
            "Luke\n16-18",
            "Luke\n19-20",
            "Luke\n21-22",
            "Luke\n23-24",
            "John\n1-2",
            "John\n3-4",
            "John\n5-6",
            "John\n7-8",
            "John\n9-10",
            "John\n11-12",
            "John\n13-15",
            "John\n16-17",
            "John\n18-19",
            "John\n20-21",
            "Acts\n1-3",
            "Acts\n4-5",
            "Acts\n6-7",
            "Acts\n8-9",
            "Acts\n10-11",
            "Acts\n12-13",
            "Acts\n14-15",
            "Acts\n16-17",
            "Acts\n18-19",
            "Acts\n20-21",
            "Acts\n22-23",
            "Acts\n24-26",
            "Acts\n27-28",
            "Romans\n1-3",
            "Romans\n4-7",
            "Romans\n8-10",
            "Romans\n11-14",
            "Romans\n15-16",
            "1 Corinthians\n1-4",
            "1 Corinthians\n5-9",
            "1 Corinthians\n10-13",
            "1 Corinthians\n14-16",
            "2 Corinthians\n1-4",
            "2 Corinthians\n5-9",
            "2 Corinthians\n10-13",
            "Galatians\n1-3",
            "Galatians\n4-6",
            "Ephesians\n1-3",
            "Ephesians\n4-6",
            "Philippians\n1-4",
            "Colossians\n1-4",
            "1 Thessalonians\n1-5",
            "2 Thessalonians\n1-3",
            "1 Timothy\n1-6",
            "2 Timothy\n1-4",
            "Philemon\n1\nTitus\n1-3",
            "Hebrews\n1-4",
            "Hebrews\n5-8",
            "Hebrews\n9-10",
            "Hebrews\n11-13",
            "James\n1-5",
            "1 Peter\n1-5\n2 Peter\n1-3",
            "1 John\n1-5",
            "2 John 1\n3 John 1\nJude 1",
            "Revelation\n1-3",
            "Revelation\n4-7",
            "Revelation\n8-11",
            "Revelation\n12-14",
            "Revelation\n15-17",
            "Revelation\n18-19",
            "Revelation\n20-22"
            //</editor-fold>
    };
    private String[] history = {
            //<editor-fold desc="history order reading plan">
            "Genesis\n1-3",
            "Genesis\n4-7",
            "Genesis\n8-11",
            "Job\n1-4",
            "Job\n5-8",
            "Job\n9-12",
            "Job\n13-16",
            "Job\n17-20",
            "Job\n21-24",
            "Job\n25-30",
            "Job\n31-34",
            "Job\n35-38",
            "Job\n39-42",
            "Genesis\n12-15",
            "Genesis\n16-18",
            "Genesis\n19-21",
            "Genesis\n22-24",
            "Genesis\n25-26",
            "Genesis\n27-29",
            "Genesis\n30-31",
            "Genesis\n32-34",
            "Genesis\n35-37",
            "Genesis\n38-40",
            "Genesis\n41-42",
            "Genesis\n43-45",
            "Genesis\n46-47",
            "Genesis\n48-50",
            "Exodus\n1-3",
            "Exodus\n4-6",
            "Exodus\n7-9",
            "Exodus\n10-12",
            "Exodus\n13-15",
            "Exodus\n16-18",
            "Exodus\n19-21",
            "Exodus\n22-24",
            "Exodus\n25-27",
            "Exodus\n28-29",
            "Exodus\n30-32",
            "Exodus\n33-35",
            "Exodus\n36-38",
            "Exodus\n39-40",
            "Leviticus\n1-4",
            "Leviticus\n5-7",
            "Leviticus\n8-10",
            "Leviticus\n11-13",
            "Leviticus\n14-15",
            "Leviticus\n16-18",
            "Leviticus\n19-21",
            "Leviticus\n22-23",
            "Leviticus\n24-25",
            "Leviticus\n26-27",
            "Numbers\n1-2",
            "Numbers\n3-4",
            "Numbers\n5-6",
            "Numbers\n7",
            "Numbers\n8-10",
            "Numbers\n11-13",
            "Numbers\n14-15\nPsalm\n90",
            "Numbers\n16-17",
            "Numbers\n18-20",
            "Numbers\n21-22",
            "Numbers\n23-25",
            "Numbers\n26-27",
            "Numbers\n28-30",
            "Numbers\n31-32",
            "Numbers\n33-34",
            "Numbers\n35-36",
            "Deuteronomy\n1-2",
            "Deuteronomy\n3-4",
            "Deuteronomy\n5-7",
            "Deuteronomy\n8-10",
            "Deuteronomy\n11-13",
            "Deuteronomy\n14-16",
            "Deuteronomy\n17-20",
            "Deuteronomy\n21-23",
            "Deuteronomy\n24-27",
            "Deuteronomy\n28-29",
            "Deuteronomy\n30-31",
            "Deuteronomy\n33-34\nPsalm 91",
            "Joshua\n1-2",
            "Joshua\n3-5",
            "Joshua\n6-7",
            "Joshua\n8-9",
            "Joshua\n10-12",
            "Joshua\n13-15",
            "Joshua\n16-18",
            "Joshua\n19-21",
            "Joshua\n22-24",
            "Judges\n1-2",
            "Judges\n3-5",
            "Judges\n6-7",
            "Judges\n8-9",
            "Judges\n10-12",
            "Judges\n13-15",
            "Judges\n16-18",
            "Judges\n19-21",
            "Ruth\n1-4",
            "1 Samuel\n1-3",
            "1 Samuel\n4-8",
            "1 Samuel\n9-12",
            "1 Samuel\n13-14",
            "1 Samuel\n15-17",
            "1 Samuel\n18-20\nPsalm\n11, 59",
            "1 Samuel\n21-24",
            "Psalm\n7, 27, 31, 34, 52",
            "Psalm\n56, 120, 140-142",
            "1 Samuel\n25-27",
            "Psalm\n 17, 35, 54, 63",
            "1 Samuel\n28-31\nPsalm\n18",
            "Psalm\n121, 123-125, 128-130",
            "2 Samuel\n1-4",
            "Psalm\n6, 8-10, 14, 16, 19, 21",
            "1 Chronicles\n1-2",
            "Psalm\n43-45, 49, 84-85, 87",
            "1 Chronicles\n3-5",
            "Psalm\n73, 77-78",
            "1 Chronicles\n6",
            "Psalm\n81, 88, 92-93",
            "1 Chronicles\n7-10",
            "Psalm\n102-104",
            "2 Samuel\n5:1-10\n1 Chronicles\n11-12",
            "Psalm\n133",
            "Psalm\n106-107",
            "2 Samuel 5:11-25\n2 Samuel 6:1-23\n1 Chr. 13-16",
            "Psalm\n1-2, 15, 22-24, 47, 68",
            "Psalm\n89, 96, 100, 101, 105, 132",
            "2 Samuel\n7\n1 Chronicles\n17",
            "Psalm\n25, 29, 33, 36, 39",
            "2 Samuel\n8-9\n1 Chronicles\n18",
            "Psalm\n50, 53, 60, 75",
            "2 Samuel 10\n1 Chronicles\n19\nPsalm 20",
            "Psalm\n65-67, 69-70",
            "2 Samuel\n11-12\n1 Chronicles\n20",
            "Psalm\n32, 51, 86, 122",
            "2 Samuel\n13-15",
            "Psalm\n3-4, 12-13, 28, 55",
            "2 Samuel\n16-18",
            "Psalm\n26, 40, 58, 61-62, 64",
            "2 Samuel\n19-21",
            "Psalm\n5, 38, 41-42",
            "2 Samuel\n22-23\nPsalm\n57",
            "Psalm\n95, 97-99",
            "2 Samuel\n24\n1 Chronicles\n21-22\nPsalm 30",
            "Psalm\n108-110",
            "1 Chronicles\n23-25",
            "Psalm\n131, 138-139, 143-145",
            "1 Chronicles\n26-29\nPsalm\n127",
            "Psalm\n111-118",
            "1 Kings\n1-2\nPsalm\n37, 71, 94",
            "Psalm\n119:1-88",
            "1 Kings\n3-4\n2 Chronicles\n1\nPsalm 72",
            "Psalm\n119:89-176",
            "Song of\nSolomon\n1-8",
            "Proverbs\n1-3",
            "Proverbs\n4-6",
            "Proverbs\n7-9",
            "Proverbs\n10-12",
            "Proverbs\n13-15",
            "Proverbs\n16-18",
            "Proverbs\n19-21",
            "Proverbs\n22-24",
            "1 Kings\n5-6\n2 Chronicles\n2-3",
            "1 Kings\n7\n2 Chronicles\n4",
            "1 Kings\n8\n2 Chronicles\n5",
            "2 Chronicles\n6-7\nPsalm\n136",
            "Psalm\n134, 146-150",
            "1 Kings\n9\n2 Chronicles\n8",
            "Proverbs\n25-26",
            "Proverbs\n27-29",
            "Ecclesiastes\n1-6",
            "Ecclesiastes\n7-12",
            "1 Kings\n10-11\n2 Chronicles\n9",
            "Proverbs\n30-31",
            "1 Kings\n12-14",
            "2 Chronicles\n10-12",
            "1 Kings\n15:1-24\n2 Chronicles\n13-16",
            "1 Kings\n15:25-34\n1 Kings 16:1-34\n2 Chronicles 17",
            "1 Kings\n17-19",
            "1 Kings\n20-21",
            "1 Kings\n22\n2 Chronicles\n18",
            "2 Chronicles\n19-23",
            "Obadiah\nPsalm\n82-83",
            "2 Kings\n1-4",
            "2 Kings\n5-8",
            "2 Kings\n9-11",
            "2 Kings\n12-13\n2 Chronicles\n24",
            "2 Kings\n14\n2 Chronicles\n25",
            "Jonah\n1-4",
            "2 Kings\n15\n2 Chronicles\n26",
            "Isaiah\n1-4",
            "Isaiah\n5-8",
            "Amos\n1-5",
            "Amos\n6-9",
            "2 Chronicles\n27\nIsaiah\n9-12",
            "Micah\n1-7",
            "2 Chronicles\n28\n2 Kings\n16-17",
            "Isaiah\n13-17",
            "Isaiah\n18-22",
            "Isaiah\n23-27",
            "2 Kings 18:1-8\n2 Chr. 29-31\nPsalm 48",
            "Hosea\n1-7",
            "Hosea\n8-14",
            "Isaiah\n28-30",
            "Isaiah\n31-34",
            "Isaiah\n35-36",
            "Isaiah\n37-39\nPsalm\n76",
            "Isaiah\n40-43",
            "Isaiah\n44-48",
            "2 Kings\n18:9-37, 19:1-37\nPsalm\n46, 80, 135",
            "Isaiah\n49-53",
            "Isaiah\n54-58",
            "Isaiah\n59-63",
            "Isaiah\n64-66",
            "2 Kings\n20-21",
            "2 Chronicles\n32-33",
            "Nahum\n1-3",
            "2 Kings\n22-23\n2 Chronicles\n34-35",
            "Zephaniah\n1-3",
            "Jeremiah\n1-3",
            "Jeremiah\n4-6",
            "Jeremiah\n7-9",
            "Jeremiah\n10-13",
            "Jeremiah\n14-17",
            "Jeremiah\n18-22",
            "Jeremiah\n23-25",
            "Jeremiah\n26-29",
            "Jeremiah\n30-31",
            "Jeremiah\n32-34",
            "Jeremiah\n35-37",
            "Jeremiah\n38-40; Psalm\n74, 79",
            "2 Kings\n24-25\n2 Chronicles\n36",
            "Habakkuk\n1-3",
            "Jeremiah\n41-45",
            "Jeremiah\n46-48",
            "Jeremiah\n49-50",
            "Jeremiah\n51-52",
            "Lamentations\n1-2, 3:1-36",
            "Lamentations\n3:37-66, 4, 5:1-22",
            "Ezekiel\n1-4",
            "Ezekiel\n5-8",
            "Ezekiel\n9-12",
            "Ezekiel\n13-15",
            "Ezekiel\n16-17",
            "Ezekiel\n18-19",
            "Ezekiel\n20-21",
            "Ezekiel\n22-23",
            "Ezekiel\n24-27",
            "Ezekiel\n28-31",
            "Ezekiel\n32-34",
            "Ezekiel\n35-37",
            "Ezekiel\n38-39",
            "Ezekiel\n40-41",
            "Ezekiel\n42-43",
            "Ezekiel\n44-45",
            "Ezekiel\n46-48",
            "Joel\n1-3",
            "Daniel\n1-3",
            "Daniel\n4-6",
            "Daniel\n7-8",
            "Daniel\n10-12",
            "Ezra\n1-3",
            "Ezra\n4-6\nPsalm\n137",
            "Haggai\n1-2",
            "Zechariah\n1-7",
            "Zechariah\n8-14",
            "Esther\n1-5",
            "Esther\n6-10",
            "Ezra\n7-10",
            "Nehemiah\n1-5",
            "Nehemiah\n6-7",
            "Nehemiah\n8-10",
            "Nehemiah\n11-13\nPsalm\n126",
            "Malachi\n1-4",
            "Luke\n1\nJohn\n1:1-14",
            "Matthew\n1\nLuke\n2:1-38",
            "Matthew\n2\nLuke\n2:39-52",
            "Matthew 3\nMark 1\nLuke 3",
            "Matthew 4\nLuke 4-5\nJohn 1:15-51",
            "John\n2-4",
            "Mark\n2",
            "John\n5",
            "Matthew\n12:1-21\nMark 3\nLuke 6",
            "Matthew\n5-7",
            "Matthew\n8:1-13\nLuke\n7",
            "Matthew\n11",
            "Matthew\n12:22-50\nLuke\n11",
            "Matthew\n13\nLuke\n8",
            "Matthew\n8:14-34\nMark\n4-5",
            "Matthew\n9-10",
            "Matthew 14\nMark 6\nLuke 9:1-17",
            "John\n6",
            "Matthew\n15\nMark\n7",
            "Matthew 16\nMark 8\nLuke 9:18-27",
            "Matthew 17\nMark 9\nLuke 9:28-62",
            "Matthew\n18",
            "John\n7-8",
            "John\n9:1-41\nJohn\n10:1-21",
            "Luke\n10-11\nJohn\n10:22-42",
            "Luke\n12-13",
            "Luke\n14-15",
            "Luke\n16, 17:1-10",
            "John\n11",
            "Luke\n17:11-37, 18:1-14",
            "Matthew\n19\nMark\n10",
            "Matthew\n20-21",
            "Luke\n18:15-43, 19:1-48",
            "Mark\n11\nJohn\n12",
            "Matthew\n22\nMark\n12",
            "Matthew\n23\nLuke\n20-21",
            "Mark\n13",
            "Matthew\n24",
            "Matthew\n25",
            "Matthew\n26\nMark\n 14",
            "Luke\n22\nJohn\n13",
            "John\n14-17",
            "Matthew\n27\nMark\n15",
            "Luke\n23\nJohn\n18-19",
            "Matthew\n28\nMark\n16",
            "Luke\n24\nJohn\n20-21",
            "Acts\n1-3",
            "Acts\n4-6",
            "Acts\n7-8",
            "Acts\n9-10",
            "Acts\n11-12",
            "Acts\n13-14",
            "James\n1-5",
            "Acts\n15-16",
            "Galatians\n1-3",
            "Galatians\n4-6",
            "Acts\n17, 18:1-18",
            "1 Thessalonians\n1-5\n2 Thessalonians\n1-3",
            "Acts\n18:19-41",
            "1 Corinthians\n1-4",
            "1 Corinthians\n5-8",
            "1 Corinthians\n9-11",
            "1 Corinthians\n12-14",
            "1 Corinthians\n15-16",
            "2 Corinthians\n1-4",
            "2 Corinthians\n5-9",
            "2 Corinthians\n10-13",
            "Acts\n20:1-3\nRomans\n1-3",
            "Romans\n4-7",
            "Romans\n8-10",
            "Romans\n11-13",
            "Romans\n14-16",
            "Acts\n20:4-38, 21-23:1-35",
            "Acts\n24-26",
            "Acts\n27-28",
            "Colossians\n1-4\nPhilemon",
            "Ephesians\n1-6",
            "Philippians\n1-4",
            "1 Timothy\n1-6",
            "Titus\n1-3",
            "1 Peter\n1-5",
            "Hebrews\n1-6",
            "Hebrews\n7-10",
            "Hebrews\n11-13",
            "2 Timothy\n1-4",
            "2 Peter\n1-3\nJude",
            "1 John\n1-5",
            "2 John\n3 John",
            "Revelation\n1-5",
            "Revelation\n6-11",
            "Revelation\n12-18",
            "Revelation\n19-22"
            //</editor-fold>
    };

    public static FragmentMain newInstance() {
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        //allow checkmark clickable once if today is different than yesterday
        complete_box = (CheckBox) view.findViewById(R.id.checkbox);

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        final SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
        //grab bible verse
        String reading_plan = settings.getString("reading_plan", null);
        //grab reading day
        int reading_day = settings.getInt("reading_day",0);
        //grab yesterday
        int lastDay = settings.getInt("day",0);

        //reset the checkmark every day
        if (lastDay != currentDay) {
            SharedPreferences.Editor editor = settings.edit();

            editor.putInt("day", currentDay);
            editor.putInt("completed", 0);
            editor.commit();

            complete_box.setChecked(false);
            complete_box.setEnabled(true);
        }

        //if reading_day is zero (just started), do not show the checkmark
        complete_layout = (RelativeLayout) view.findViewById(R.id.complete_layout);
        complete_button_background = (TextView) view.findViewById(R.id.complete_button_background);

        if (reading_plan == null) {
            complete_layout.setVisibility(View.INVISIBLE);
            complete_button_background.setVisibility(View.INVISIBLE);
        }

        //if the checkmark is clicked (completed), keep it complete even if the app is restarted
        int completed = settings.getInt("completed",0);
        if (completed == 1) {
            complete_box.setChecked(true);
            complete_box.setEnabled(false);
        }

        //if the checkmark is clicked, freeze it (so that reading day won't count anymore)
        complete_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (complete_box.isChecked()){
                    complete_box.setEnabled(false);

                    SharedPreferences.Editor editor = settings.edit();
                    int reading_day = settings.getInt("reading_day",0);
                    reading_day++;
                    editor.putInt("reading_day", reading_day);

                    editor.putInt("completed", 1);
                    editor.commit();
                }
            }
        });

        //set date
        date_text = (TextView) view.findViewById(R.id.date);
        //grab today's date
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd EEEE");
        String date = formatter.format(today);
        date_text.setText(date);

        //grab day
        day_text = (TextView) view.findViewById(R.id.day);
        //because reading_day starts with 1, it does not need to be incremented for Day
        if (reading_plan != null && !reading_plan.isEmpty() && reading_day != 0) {
            day_text.setText("Day " + reading_day);
        }
        //remove the string "Day 1" if the app does not have any reading plan
        else {
            day_text.setText("");
        }

        //bible verse
        verse_text = (TextView) view.findViewById(R.id.verse);
        String verse;

        //check if an alarm is set
        if (reading_plan != null && reading_day != 0)
        {
            //the reading_day is set to 1 when the alarm is set, so decrease 1 for the array
            reading_day--;
            if (reading_plan.equals("ninety_days")) {
                verse = ninety[reading_day];
                verse_text.setText(verse);
            }
            else if (reading_plan.equals("order_bible")) {
                verse = bible[reading_day];
                verse_text.setText(verse);
            }
            else if (reading_plan.equals("order_history")) {
                verse = history[reading_day];
                verse_text.setText(verse);
            }
            else {
                verse_text.setText("Set\nAlarm\nto\nStart!");
                day_text.setText("");
            }
        }

        //setting button
        btn_setting = (Button) view.findViewById(R.id.setting_button);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(getActivity(), Setting.class);
                startActivityForResult(setting, 20002);
            }
        });

        //set font_size
        String font_size = settings.getString("font_size", null);
        String language = settings.getString("language", null);
        if (font_size != null && language != null) {
            /* font_size */
            if (font_size.equals("large_font")) {
                adjustFontSize(getResources().getConfiguration(), (float) 1.2);
            }
            else if (font_size.equals("small_font")) {
                adjustFontSize(getResources().getConfiguration(), (float) 1);
            }
            /* language */
            if (language.equals("english")) {
                //FIXME: set language
            }
            else if (language.equals("korean")) {
                //FIXME: set language
            }
        }

        //credit button
        btn_credit = (Button) view.findViewById(R.id.credit_button);
        btn_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Credits.class));
            }
        });

        return view;
    }

    private void adjustFontSize(Configuration configuration, float scale) {
        configuration.fontScale = scale;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getActivity().getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
//        if ((requestCode == 10001) && (resultCode == Activity.RESULT_OK)) {
        // recreate your fragment here
        Fragment frg = null;
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
//        }
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
