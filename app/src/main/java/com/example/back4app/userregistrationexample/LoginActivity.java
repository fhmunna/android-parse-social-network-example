package com.example.back4app.userregistrationexample;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.LogInCallback;

import static com.example.back4app.userregistrationexample.PreferenceKey.KEEP_ME_LOOGED;
import static com.example.back4app.userregistrationexample.PreferenceKey.MY_USER_NAME_KEY;
import static com.example.back4app.userregistrationexample.PreferenceKey.My_PASSWORD_KEY;
import static com.example.back4app.userregistrationexample.PreferenceKey.REGISTARED;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameView;
    private EditText passwordView;
    private CheckBox checkBoxKeepMeLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this);
        SharedPref.init(getApplicationContext());

        checkBoxKeepMeLogged = (CheckBox) findViewById(R.id.checkBoxKeepMeLogged);




        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);

        final Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validating the log in data
                boolean validationError = false;

                StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
                if (isEmpty(usernameView)) {
                    validationError = true;
                    validationErrorMessage.append("an username");
                }
                if (isEmpty(passwordView)) {
                    if (validationError) {
                        validationErrorMessage.append(" and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("a password");
                }
                validationErrorMessage.append(".");

                if (validationError) {
                    Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }

                //Setting up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Logging in...");
                dlg.show();

                ParseUser.logInInBackground(usernameView.getText().toString(), passwordView.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if (checkBoxKeepMeLogged.isChecked()){
                            SharedPref.write(MY_USER_NAME_KEY,usernameView.getText().toString());
                            SharedPref.write(My_PASSWORD_KEY,passwordView.getText().toString());
                            SharedPref.write(KEEP_ME_LOOGED,"true");
                        }


                        if (parseUser != null) {
                            if (parseUser.getBoolean("emailVerified")) {
                                dlg.dismiss();
                                alertDisplayer("Login Sucessful", "Welcome, " + parseUser.getUsername().toString() + "!", false);
                            } else {
                                ParseUser.logOut();
                                dlg.dismiss();
                                alertDisplayer("Login Fail", "Please Verify Your Email first", true);
                            }
                        } else {
                            ParseUser.logOut();
                            dlg.dismiss();
                            alertDisplayer("Login Fail", e.getMessage() + " Please re-try", true);
                        }
                    }
                });
            }
        });

        final Button signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private void alertDisplayer(String title, String message, final boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if (!error) {

                            if (SharedPref.read(REGISTARED).equalsIgnoreCase("false")) {

//                            Intent intent = new Intent(LoginActivity.this, LogoutActivity.class);
                                Intent intent = new Intent(LoginActivity.this, ProfileCategoryActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {


//                            Intent intent = new Intent(LoginActivity.this, LogoutActivity.class);
                                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }


                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}