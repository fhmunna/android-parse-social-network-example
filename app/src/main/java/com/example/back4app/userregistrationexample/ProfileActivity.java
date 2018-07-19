package com.example.back4app.userregistrationexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView textView_username;
    TextView textView_password;
    ListView Education_List;
    ArrayList<Education> mEducation;
    EducationAdapter meAdapter;
    Button addMoreEducation;
    int width;
    int height;
   boolean currentInstitutionStatus = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        addMoreEducation = (Button) findViewById(R.id.addMoreEducation);
        mEducation = new ArrayList<Education>();

        Education_List = (ListView) findViewById(R.id.Education_List);
        textView_username = (TextView) findViewById(R.id.textView_username);
        textView_password = (TextView) findViewById(R.id.textView_password);

        meAdapter = new EducationAdapter(ProfileActivity.this, mEducation);

        Education_List.setAdapter(meAdapter);


        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
         /*   textView_username.setText(currentUser.getUsername());
            textView_password.setText(currentUser.getObjectId());
*/

            ParseQuery<Education> query = ParseQuery.getQuery(Education.class);
            query.findInBackground(new FindCallback<Education>() {
                @Override
                public void done(List<Education> objects, ParseException e) {
                    Log.d("adapter_chk", "hoy ");


                    for (Education edu : objects) {

                        mEducation.add(edu);

                    }


                    meAdapter.notifyDataSetChanged();

                }
            });


        } else {
            // show the signup or login screen
        }


        final ParseUser current = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            textView_username.setText(current.getUsername());
            textView_password.setText(current.getObjectId());

        } else {
            // show the signup or login screen
        }


        addMoreEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openEduAddDialog();


            }
        });


    }

    private void openEduAddDialog() {




        //check if there is a custom limit
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_add_education, null);


        final LinearLayout institutionendingLayout = (LinearLayout) alertLayout.findViewById(R.id.institutionendingLayout);
        final EditText institutionName = (EditText) alertLayout.findViewById(R.id.institutionName);
        final EditText degree = (EditText) alertLayout.findViewById(R.id.degree);
        final EditText fieldOfStudy = (EditText) alertLayout.findViewById(R.id.fieldOfStudy);
        final EditText startingDate = (EditText) alertLayout.findViewById(R.id.startingDate);
        final CheckBox isCurrent = (CheckBox) alertLayout.findViewById(R.id.isCurrent);
        final EditText endingDate = (EditText) alertLayout.findViewById(R.id.endingDate);
        final EditText description = (EditText) alertLayout.findViewById(R.id.description);
        Button profile_category_save_button = (Button) alertLayout.findViewById(R.id.profile_category_save_button);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {


                currentInstitutionStatus = true;


            }

        });


        isCurrent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isCurrent.isChecked()) {
//                    System.out.println("Checked");
                    institutionendingLayout.setVisibility(View.GONE);
                    currentInstitutionStatus = true;

                } else {
                    currentInstitutionStatus = false;
                    institutionendingLayout.setVisibility(View.VISIBLE);
//                    System.out.println("Un-Checked");
                }
            }
        });


        profile_category_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (updateUserCategory(1)) {
                final ParseUser currentUser = ParseUser.getCurrentUser();
//                    ParseObject education = new ParseObject("Education");
                Education education = new Education();
                education.put("userId", currentUser.getObjectId());
                education.put("institutionName", institutionName.getText().toString());
                education.put("degree", degree.getText().toString());
                education.put("fieldOfStudy", fieldOfStudy.getText().toString());
                education.put("startingDate", startingDate.getText().toString());
                if (currentInstitutionStatus) {
                    education.put("isCurrent", "1");
                    education.put("endingDate", "0");
                } else {
                    education.put("isCurrent", "0");
                    education.put("endingDate", endingDate.getText().toString());
                }
                education.put("description", description.getText().toString());
                education.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

             /*                   Intent intent = new Intent(ProfileCategoryActivity.this, Locationactivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/

                            dialog.dismiss();
                            meAdapter.notifyDataSetChanged();

                        }
                    }
                });

        /*        } else {

                    Log.d("CAT", "user category key error");

                }*/


            }
        });


        dialog.show();
        int customwidth = width - 100;
        dialog.getWindow().setLayout(customwidth, 650);
    }



}
