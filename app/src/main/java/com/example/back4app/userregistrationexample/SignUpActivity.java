package com.example.back4app.userregistrationexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String gender_value;
    private int yearPatient, monthPatient, dayPatient;
    String getDay, getMonth, getYear;
    private Calendar mCalendarForAge;
    boolean flagForCheckDate = false, flagForCurrentday = false, flagForDatePick = false;

    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordAgainView;
    EditText et_first_name;
    EditText et_last_name;
    EditText et_gender;
    EditText et_phone;
    EditText et_email;
    EditText et_dob;

    Spinner gender_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        Parse.initialize(this);


        //age calculate
        mCalendarForAge = Calendar.getInstance();
        yearPatient = mCalendarForAge.get(Calendar.YEAR);
        monthPatient = mCalendarForAge.get(Calendar.MONTH);
        dayPatient = mCalendarForAge.get(Calendar.DAY_OF_MONTH);


        final Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Returning to the login section...");
                dlg.show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                dlg.dismiss();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        passwordAgainView = (EditText) findViewById(R.id.passwordAgain);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_last_name = (EditText) findViewById(R.id.et_last_name);
//        et_gender = (EditText) findViewById(R.id.et_gender);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_dob = (EditText) findViewById(R.id.et_dob);

        gender_spinner = (Spinner) findViewById(R.id.gender_spinner);


        //create a list of items for the spinner.
        String[] items = new String[]{"Male", "Female", "Other"};

        gender_spinner.setOnItemSelectedListener(this);
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        gender_spinner.setAdapter(adapter);

        final Button signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
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
                if (isEmpty(passwordAgainView)) {
                    if (validationError) {
                        validationErrorMessage.append(" and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("your password again");
                } else {
                    if (!isMatching(passwordView, passwordAgainView)) {
                        if (validationError) {
                            validationErrorMessage.append(" and ");
                        }
                        validationError = true;
                        validationErrorMessage.append("the same password twice.");
                    }
                }
                validationErrorMessage.append(".");

                if (validationError) {
                    Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }

                //Setting up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Signing up...");
                dlg.show();

                ParseUser user = new ParseUser();
                user.setUsername(usernameView.getText().toString());
                user.setPassword(passwordView.getText().toString());
                user.setEmail(et_email.getText().toString());
//                user.setfirstName(et_email.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            dlg.dismiss();
                            alertDisplayer("Account Created Successfully!", "Please verify your email before Login", false);
                            saveAditional();


                        } else {
                            dlg.dismiss();
                            ParseUser.logOut();
//                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            alertDisplayer("Error Account Creation failed", "Account could not be created" + " :" + e.getMessage(), true);
                        }
                    }
                });

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

    private boolean isMatching(EditText text1, EditText text2) {
        if (text1.getText().toString().equals(text2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

/*    private void alertDisplayer(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(SignUpActivity.this, ProfileCategoryActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }*/
private void alertDisplayer(String title,String message, final boolean error){
    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    if(!error) {
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
    AlertDialog ok = builder.create();
    ok.show();
}

    private void saveAditional() {


        final ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
//                query.whereEqualTo("username",currentUser.getUsername());
        query.getInBackground(currentUser.getObjectId(), new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {

                if (e == null) {


                    object.put("firstName", et_first_name.getText().toString());
                    object.put("lastName", et_last_name.getText().toString());
                    object.put("gender", gender_value);
                    object.put("phone", et_phone.getText().toString());
                    object.put("dob", et_dob.getText().toString());
                    object.put("profileType", "0");
                    object.put("rate",0.0);
                    object.saveInBackground();




                    Log.d("size ", "Ok");
                } else {

                    Log.d("size ", String.valueOf(e));
                    e.printStackTrace();


                }
            }

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                gender_value = "Male";
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                gender_value = "Female";

                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                gender_value = "Other";
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }




    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, R.style.TimePicker,
                    myDateListener, yearPatient, monthPatient, dayPatient);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                    getDay = String.valueOf(arg3);
                    getMonth = String.valueOf(arg2 + 1);
                    getYear = String.valueOf(arg1);

                    flagForDatePick = true;

                    //check valid date
                    checkValidDate(arg1, arg2, arg3);


                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String dateForParse = getDay + "/" + getMonth + "/" + getYear;
                    Date birthDate = null;
                    try {
                        birthDate = sdf.parse(dateForParse);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                    //object of age
//                    ageForPatientProfile = presenter.calculateAge(birthDate);

                    //birth day
//                    birthDayPateint = String.valueOf(ageForPatientProfile.getDays());

                    //birth Month
//                    birthMonthPatient = String.valueOf(ageForPatientProfile.getMonths());

                    //birth Year
//                    birthYearPatient = String.valueOf(ageForPatientProfile.getYears());


                }
            };



    /**
     * Check Valid date when from pick calendar
     * @param   year means you pick from calendar that year
     * @param   month means you pick from calendar that month
     * @param   day means you pick from calendar that day
     *
     * */
    public void checkValidDate(int year, int month, int day){

        Calendar checkValidDate = Calendar.getInstance();
        if (checkValidDate.get(Calendar.YEAR) == year) {
            //current year
            if (checkValidDate.get(Calendar.MONTH) + 1 < month + 1) {
                //big month
                //not valid
                flagForCheckDate = true;
            } else if (checkValidDate.get(Calendar.MONTH) + 1 == month + 1) {
                //equal month
                if (checkValidDate.get(Calendar.DAY_OF_MONTH) < day) {
                    //big day
                    flagForCheckDate = true;
                    //not valid
                } else if (checkValidDate.get(Calendar.DAY_OF_MONTH) == day) {
                    //same day
                    //valid
                    flagForCheckDate = false;
                    flagForCurrentday = true;


                } else {
                    flagForCheckDate = false;
                }

            }
            else if (checkValidDate.get(Calendar.MONTH) + 1 > month + 1){
                // selected month is small from current month
                flagForCheckDate = false;
            }
        } else if (checkValidDate.get(Calendar.YEAR) < year) {
            //selected year is big from current year
            flagForCheckDate = true;
        } else if (checkValidDate.get(Calendar.YEAR) > year) {
            //selected year is small from current year
            flagForCheckDate = false;
        }

    }

    /**
     * Show birth date in the edit text field
     * */
    private void showDate(int year, int month, int day) {
        et_dob.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


}
