package com.example.back4app.userregistrationexample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class LogoutActivity extends AppCompatActivity {

    Button update_button;

    String fName, lName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this);
        setContentView(R.layout.activity_logout);


<<<<<<< HEAD
        textView_username = (TextView) findViewById(R.id.textView_username);
        textView_password = (TextView) findViewById(R.id.textView_password);
//        et_first_name = (EditText) findViewById(R.id.et_first_name);
//        et_last_name = (EditText) findViewById(R.id.et_last_name);
=======

>>>>>>> f84874378717e1243236ee18647f1377c1773d82





//        update_button = (Button) findViewById(R.id.update_button);

    /*    update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fName = et_first_name.getText().toString();
                lName = et_last_name.getText().toString();


                ParseQuery<ParseUser> query = ParseUser.getQuery();
//                query.whereEqualTo("username",currentUser.getUsername());
                query.getInBackground(currentUser.getObjectId(), new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser object, ParseException e) {

                        if (e == null) {
                            object.put("firstName", fName);
                            object.put("lastName", lName);
                            object.saveInBackground();

                            Log.d("size ", "Ok");
                        } else {

                            Log.d("size ", String.valueOf(e));
                            e.printStackTrace();


                        }


                    }
                });


            }
        });
*/

        final Button logout_button = findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dlg = new ProgressDialog(LogoutActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Signing Out...");
                dlg.show();

                // logging out of Parse
                ParseUser.logOut();

                alertDisplayer("So, you're going...", "Ok...Bye-bye then");

            }
        });
    }

    private void alertDisplayer(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LogoutActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}