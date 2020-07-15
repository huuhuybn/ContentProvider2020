package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import java.security.Permission;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        String path = "content://contacts/people";
        Uri uri = Uri.parse(path);
        CursorLoader cursorLoader =
                new CursorLoader(this,
                        ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // them cau lenh hoi nguoi dung nguoi quyen doc danh ba

        if (ContextCompat.checkSelfPermission(ContactActivity.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            // chay query vao duong dan Path
            Cursor cursor = cursorLoader.loadInBackground();
            if (cursor.getCount() > 0) {
                Toast.makeText(this, "CO DANH BA :" + cursor.getCount(),
                        Toast.LENGTH_SHORT).show();

                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    cursor.moveToNext();
                }

            } else {
                Toast.makeText(this, "KO CO DANH BA NAO ???", Toast.LENGTH_SHORT).show();
            }
        } else {
            // xin quyen
            ActivityCompat.requestPermissions(
                    ContactActivity.this, new String[]{Manifest.permission.READ_CONTACTS},
                    999);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Cam on ban da dong y !!!", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "Vay thi ko xem dc contacts nhe!!!", Toast.LENGTH_SHORT).show();
        }

    }
}