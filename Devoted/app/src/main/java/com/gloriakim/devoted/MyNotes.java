package com.gloriakim.devoted;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyNotes extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private EditText editText, userIDText;
    private Button btn_save, btn_close;
    private CheckBox check_public, check_private;
    private boolean open_to_public;
    private String new_note, new_userID;
    private int max_num = 0;
    private String current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        //database
        mDatabaseHelper = new DatabaseHelper(this);

//        FIXME: removed the date for now to code faster
//        //string date
//        Calendar calendar = Calendar.getInstance();
//        current_date = DateFormat.getDateInstance().format(calendar.getTime());
//
//        TextView date = findViewById(R.id.date);
//        date.setText(current_date);
//
//        current_date = DateFormat.getDateInstance().format(calendar.getTime());

        //check marks
        check_public = (CheckBox) findViewById(R.id.check_public);
        check_private = (CheckBox) findViewById(R.id.check_private);

        check_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_public.isChecked()) {
                    open_to_public = true;
                    check_private.setChecked(false);
                }
                else {
                    open_to_public = false;
                    check_private.setChecked(true);
                }
            }
        });

        check_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check only one of public or private check-marks
                if (check_private.isChecked()) {
                    open_to_public = false;
                    check_public.setChecked(false);
                }
                else {
                    open_to_public = true;
                    check_public.setChecked(true);
                }
            }
        });

        //edit text
        editText = (EditText) findViewById(R.id.edit_text);
        userIDText = (EditText) findViewById(R.id.user_id_text);

        //save button
        btn_save = (Button) findViewById(R.id.save_button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit text
                 new_note = editText.getText().toString();
                 new_userID = userIDText.getText().toString();

                 if (editText.length() == 0) {
                     toastMessage("Please write your note.");
                 }
                 else if (userIDText.length() == 0) {
                    if (open_to_public == true) {
                        toastMessage("Please write your ID.");
                    }
                }
                else if (editText.length() != 0 && userIDText.length() != 0) {
                    //save private notes
                    if (open_to_public == false) {
                        AddPrivateData(new_note);
                    } else {
                        AddPublicData(new_note, new_userID);
                    }
                    //reset the edit texts
                    editText.setText("");
                    userIDText.setText("");
                    //close the window
                    finish();
                }
                else {
                    toastMessage("There is an error to create your note.");
                }
            }
        });

        //close button
        btn_close = (Button) findViewById(R.id.close_button);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void AddPrivateData(String note) {
        //add the note to the internal storage using SQLite
        boolean insertData = mDatabaseHelper.addData(note);
        if (insertData){
            toastMessage("Your note has been successfully saved in private space.");
        } else {
            toastMessage("There was an error to save your note.");
        }
    }

    public void AddPublicData(final String note, String userID) {
        //add data using firebase
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference userNameRef = rootRef.child("Note").child(userID);
        //check if userID exists
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    //create new user
                    userNameRef.setValue(note);
                    toastMessage("Your note has been successfully saved in public space.");
                }
                else {
                    toastMessage("The User ID is already existed.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                toastMessage("There is an error to search if the user ID exists.");
            }
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}