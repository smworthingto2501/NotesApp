package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    int noteid = -1;
    String noteContent = "";
    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Get EditTextView
        editText = (TextView) findViewById(R.id.editTextTextPersonName);

        //Get Intent & value of noteid, initialise class variable noteid
        noteid = getIntent().getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            noteContent = note.getContent();
            editText.setText(noteContent);
            Log.d("content", noteContent);
        }
        Log.d("noteid", "" + noteid);

    }

    public void onClick(View view){
        //if (noteid != -1) {
            //Note note = MainActivity2.notes.get(noteid);
            noteContent = editText.getText().toString();
            editText.setText(noteContent);
       // }

        //get SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        //initiate notes class variable
        DBHelper dbHelper  = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, noteContent, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNotes(title,date, noteContent, username);
        }

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
    }
}