package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString("username", "").equals("")){
            Bundle bundle = new Bundle();
            bundle.putString("username", sharedPreferences.getString("username", user));
            bundle.putString("password", sharedPreferences.getString("password", pass));

            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            setContentView(R.layout.activity_main);
        }
    }

    public void onClick(View view){
        EditText username = (EditText) findViewById(R.id.editTextUser);
        user = username.getText().toString();
        EditText password = (EditText) findViewById(R.id.editTextPass);
        pass = password.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", user).apply();
        sharedPreferences.edit().putString("password", pass).apply();

        Bundle bundle = new Bundle();
        bundle.putString("username", user);
        bundle.putString("password",pass);

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}