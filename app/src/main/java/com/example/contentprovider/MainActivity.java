package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void contact(View view) {
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }

    public void media(View view) {

        Intent intent = new Intent(MainActivity.this, MediaStoreActivity.class);
        startActivity(intent);
    }

    public void callLog(View view) {

        Intent intent = new Intent(MainActivity.this, CallLogActivity.class);
        startActivity(intent);
    }
}