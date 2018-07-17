package com.example.back4app.userregistrationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView textView_username;
    TextView textView_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);





        textView_username = (TextView) findViewById(R.id.textView_username);
        textView_password = (TextView) findViewById(R.id.textView_password);



        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            textView_username.setText(currentUser.getUsername());
            textView_password.setText(currentUser.getObjectId());

        } else {
            // show the signup or login screen
        }












    }
}
