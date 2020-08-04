package com.example.dailybible3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentNotes extends Fragment {

    private static final String TAG = "";
    private View view;
    private Button btn_all_notes, btn_my_notes;
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    ListAdapter adapter;
    ArrayList<String> listData, listData2;

    public static FragmentNotes newInstance() {
        FragmentNotes fragmentNotes = new FragmentNotes();
        return fragmentNotes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notes, container, false);

        //SQL
        mListView = (ListView) view.findViewById(R.id.list_view);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        populatePrivateListView();

        //all note button
        btn_all_notes = (Button) view.findViewById(R.id.btn_all_notes);
        btn_all_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populatePublicListView();
            }
        });

        //my note button
        btn_my_notes = (Button) view.findViewById(R.id.btn_my_notes);
        btn_my_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populatePrivateListView();
            }
        });

        //floating button
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myNote  = new Intent(getActivity(), MyNotes.class);
                startActivityForResult(myNote, 10001);
            }
        });

        return view;
    }

    private void populatePrivateListView() {

        Log.d(TAG, "populatePrivateListView: Displaying PRIVATE data in the ListView.");

        //get the data and append to a list
        Cursor cursor = mDatabaseHelper.getData();
        listData = new ArrayList<>();

        while(cursor.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(cursor.getString(1));
        }

        //create the list adapter and set the adapter
        //customize the listview with "list_view_item"
        adapter = new ArrayAdapter<String>(getContext(),R.layout.list_view_items, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editPrivateData  = new Intent(getActivity(), FragmentNotesEditPrivateData.class);
                    editPrivateData.putExtra("id", itemID);
                    editPrivateData.putExtra("name", name);
                    //set the requetCode to update this fragment
                    startActivityForResult(editPrivateData, 10001);

                }
                else{
//                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    private void populatePublicListView() {
        DatabaseReference firebase;

        firebase = FirebaseDatabase.getInstance().getReference().child("Note");

        //set the listview
        listData2 = new ArrayList<>();

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if  (snapshot.exists()) {
                    listData2.clear();
                    String note = snapshot.getValue().toString();
                    String[] parts = note.replace("{", "").replace("}", "").replace("=", " / ").split(", ");
                    for (int i = 0; i < parts.length; i++) {
                        listData2.add(parts[i]);
                    }
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.list_view_items, listData2);
                    mListView.setAdapter(adapter);
                }
                else {
                    toastMessage("There is no public data.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set the listview
        adapter = new ArrayAdapter<String>(getContext(),R.layout.list_view_items, listData2);
        mListView.setAdapter(adapter);
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
