/* the codes related to SQL is from here: https://www.youtube.com/watch?v=nY2bYJyGty8 */

package com.gloriakim.devoted;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentNotesEditPrivateData extends AppCompatActivity {

    private static final String TAG = "FragmentNotesEditPrivateData";

    DatabaseHelper mDatabaseHelper;
    private EditText editText;
    private Button btn_save, btn_delete;
    private CheckBox check_public, check_private;
    private boolean open_to_public;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes_edit);

        //database
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from FragmentNotes
        Intent receivedIntent = getIntent();

        //grab the itemID and name extra that was passed from FragmentNotes
        //set the default value as -1
        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");

        //edit text
        editText = (EditText) findViewById(R.id.edit_text);

        //set the text to show the current selected name
        editText.setText(selectedName);

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

        //save button
        btn_save = (Button) findViewById(R.id.save_button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit text
                String note = editText.getText().toString();
                if (editText.length() != 0) {
                    //save private notes
                    if (open_to_public == false) {
                        //AddPrivateData(new_note);
                        mDatabaseHelper.updateName(note, selectedID, selectedName);
                        toastMessage("Your note has been successfully updated.");
                    } else {
                        //AddPublicData(new_note);
                    }
                    //close the window (go back to FragmentNotes)
                    finish();

                } else {
                    toastMessage("Please write your notes.");
                }
            }
        });

        //delete button
        btn_delete = (Button) findViewById(R.id.delete_button);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (open_to_public == false) {
                    //AddPrivateData(new_note);
                    mDatabaseHelper.deleteName(selectedID, selectedName);
                    editText.setText("");
                    toastMessage("Your note has been successfully deleted.");
                } else {
                    //AddPublicData(new_note);
                }
                //close the window (go back to FragmentNotes)
                finish();
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
