package com.example.back4app.userregistrationexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileCategoryActivity extends AppCompatActivity {

    boolean currentJobStatus = true;
    boolean currentInstitutionStatus = true;

    int  targetId;


    private int yearPatient, monthPatient, dayPatient;
    String getDay, getMonth, getYear;
    private Calendar mCalendarForAge;
    boolean flagForCheckDate = false, flagForCurrentday = false, flagForDatePick = false;





    boolean result;
    EditText companyName;
    EditText title;
    EditText location;
    EditText jobstartingDate;
    CheckBox isCurrentJob;
    EditText jobendingDate;
    EditText jobdescription;

    Button profile_category_save_button_job;



    EditText institutionName;
    EditText degree;
    EditText fieldOfStudy;
    EditText startingDate;
    CheckBox isCurrent;
    EditText endingDate;
    EditText description;

    Button profile_category_save_button;

    Switch simpleSwitch;

    LinearLayout studentLayout;
    LinearLayout employeeLayout;

    LinearLayout institutionendingLayout;
    LinearLayout jobendingLayout;

    Boolean switchState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_category);


        //age calculate
        mCalendarForAge = Calendar.getInstance();
        yearPatient = mCalendarForAge.get(Calendar.YEAR);
        monthPatient = mCalendarForAge.get(Calendar.MONTH);
        dayPatient = mCalendarForAge.get(Calendar.DAY_OF_MONTH);




        companyName = (EditText) findViewById(R.id.companyName);
        title = (EditText) findViewById(R.id.title);
        location = (EditText) findViewById(R.id.location);
        jobstartingDate = (EditText) findViewById(R.id.jobstartingDate);
        isCurrentJob = (CheckBox) findViewById(R.id.isCurrentJob);
        jobendingDate = (EditText) findViewById(R.id.jobendingDate);
        jobdescription = (EditText) findViewById(R.id.jobdescription);

        profile_category_save_button_job = (Button) findViewById(R.id.profile_category_save_button_job);






        institutionName = (EditText) findViewById(R.id.institutionName);
        degree = (EditText) findViewById(R.id.degree);
        fieldOfStudy = (EditText) findViewById(R.id.fieldOfStudy);
        startingDate = (EditText) findViewById(R.id.startingDate);
        isCurrent = (CheckBox) findViewById(R.id.isCurrent);
        endingDate = (EditText) findViewById(R.id.endingDate);
        description = (EditText) findViewById(R.id.description);




        profile_category_save_button = (Button) findViewById(R.id.profile_category_save_button);

        simpleSwitch = (Switch) findViewById(R.id.switch1);

        simpleSwitch.setChecked(false);
        switchState = simpleSwitch.isChecked();

        studentLayout = (LinearLayout) findViewById(R.id.studentLayout);
        employeeLayout = (LinearLayout) findViewById(R.id.employeeLayout);

        institutionendingLayout = (LinearLayout) findViewById(R.id.institutionendingLayout);
        jobendingLayout = (LinearLayout) findViewById(R.id.jobendingLayout);





        profile_category_save_button_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (updateUserCategory(2)) {
                    final ParseUser currentUser = ParseUser.getCurrentUser();
                    ParseObject experience = new ParseObject("Experience");
                    experience.put("userId", currentUser.getObjectId());
                    experience.put("companyName", companyName.getText().toString());
                    experience.put("title", title.getText().toString());
                    experience.put("location", location.getText().toString());
                    experience.put("jobstartingDate", jobstartingDate.getText().toString());
                    if (currentJobStatus){
                        experience.put("isCurrentJob", "1");
                        experience.put("jobendingDate", "0");
                    }else{
                        experience.put("isCurrentJob", "0");
                        experience.put("jobendingDate", jobendingDate.getText().toString());
                    }
/*                    experience.put("isCurrentJob", isCurrentJob.getText().toString());
                    experience.put("jobendingDate", jobendingDate.getText().toString());*/
                    experience.put("jobdescription", jobdescription.getText().toString());
                    experience.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                Intent intent = new Intent(ProfileCategoryActivity.this, Locationactivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        }
                    });

                } else {

                    Log.d("CAT", "user category key error");

                }


            }
        });










        profile_category_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (updateUserCategory(1)) {
                    final ParseUser currentUser = ParseUser.getCurrentUser();
                    ParseObject education = new ParseObject("Education");
                    education.put("userId", currentUser.getObjectId());
                    education.put("institutionName", institutionName.getText().toString());
                    education.put("degree", degree.getText().toString());
                    education.put("fieldOfStudy", fieldOfStudy.getText().toString());
                    education.put("startingDate", startingDate.getText().toString());
                    if (currentInstitutionStatus){
                        education.put("isCurrent", "1");
                        education.put("endingDate", "0");
                    }else{
                        education.put("isCurrent", "0");
                        education.put("endingDate", endingDate.getText().toString());
                    }
                    education.put("description", description.getText().toString());
                    education.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                Intent intent = new Intent(ProfileCategoryActivity.this, Locationactivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }
                        }
                    });

                } else {

                    Log.d("CAT", "user category key error");

                }


            }
        });


        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

                if (!switchState) {
                    simpleSwitch.setText("I am Employee");
                    studentLayout.setVisibility(View.GONE);
                    employeeLayout.setVisibility(View.VISIBLE);
                    switchState = true;
                } else {
                    simpleSwitch.setText("I am student");
                    studentLayout.setVisibility(View.VISIBLE);
                    employeeLayout.setVisibility(View.GONE);

                    switchState = false;

                }


            }
        });






        isCurrent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isCurrent.isChecked()){
//                    System.out.println("Checked");
                    institutionendingLayout.setVisibility(View.GONE);
                    currentInstitutionStatus = true;

                }else{
                    currentInstitutionStatus = false;
                    institutionendingLayout.setVisibility(View.VISIBLE);
//                    System.out.println("Un-Checked");
                }
            }
        });


        isCurrentJob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isCurrentJob.isChecked()){
//                    System.out.println("Checked");
                    jobendingLayout.setVisibility(View.GONE);
                    currentJobStatus = true;

                }else{
                    currentJobStatus = false;
                    jobendingLayout.setVisibility(View.VISIBLE);
//                    System.out.println("Un-Checked");
                }
            }
        });






    }


    private boolean updateUserCategory(final int profileType) {


        final ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
//                query.whereEqualTo("username",currentUser.getUsername());
        query.getInBackground(currentUser.getObjectId(), new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e == null) {
                    object.put("profileType", String.valueOf(profileType));
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                result = true;
                            } else {
                                result = false;

                            }


                        }
                    });

                    Log.d("size ", "Ok");
//                    result[0] = true;
                } else {
//                    result[0] = false;
                    Log.d("size ", String.valueOf(e));
                    e.printStackTrace();


                }
            }

        });


        Log.d("size ", String.valueOf(result));
        return result;


    }




    @SuppressWarnings("deprecation")
    public void setDate(View view) {

        targetId = view.getId();
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

        EditText et_dob = (EditText) findViewById(targetId);

        et_dob.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


}
