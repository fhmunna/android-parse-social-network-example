package com.example.back4app.userregistrationexample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_username;
    TextView textView_password;
    ListView Education_List;
    ListView Experience_List;

    RecyclerView recycler_academic_list;
    RecyclerView recycler_experience_list;


    ArrayList<Education> mEducation;
    ArrayList<Experience> mExperience;

    EducationReAdaptere meReAdapter;
    ExperienceReadapter mexReAdapter;

    EducationAdapter meAdapter;
    ExperienceAdapter mexAdapter;

    Button addMoreEducation;
    Button addMoreExperience;
    Button home_btn;
    Button friend_btn;
    Button chat_btn;
    Button logout_btn;

    int width;
    int height;
    boolean currentInstitutionStatus = true;
    boolean currentJobStatus = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        addMoreEducation = (Button) findViewById(R.id.addMoreEducation);
        addMoreExperience = (Button) findViewById(R.id.addMoreExperience);
        logout_btn = (Button) findViewById(R.id.logout_btn);
        chat_btn = (Button) findViewById(R.id.chat_btn);
        friend_btn = (Button) findViewById(R.id.friend_btn);
        home_btn = (Button) findViewById(R.id.home_btn);


        mEducation = new ArrayList<Education>();
        mExperience = new ArrayList<Experience>();

/*        Education_List = (ListView) findViewById(R.id.Education_List);
        Experience_List = (ListView) findViewById(R.id.Experience_List);*/

        recycler_academic_list = (RecyclerView) findViewById(R.id.recycler_academic_list);
        recycler_experience_list = (RecyclerView) findViewById(R.id.recycler_experience_list);

        pullEducation();
        pullExperience();

        meReAdapter = new EducationReAdaptere(this, mEducation);
        recycler_academic_list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_academic_list.setLayoutManager(layoutManager);
        recycler_academic_list.setAdapter(meReAdapter);

        mexReAdapter = new ExperienceReadapter(this, mExperience);
        recycler_experience_list.setHasFixedSize(true);
        RecyclerView.LayoutManager experiencelayoutManager = new LinearLayoutManager(this);
        recycler_experience_list.setLayoutManager(experiencelayoutManager);
        recycler_experience_list.setAdapter(mexReAdapter);


/*
        Education_List.setScrollContainer(false);
        Experience_List.setScrollContainer(false);
*/

/*
        justifyListViewHeightBasedOnChildren(Education_List);
        justifyListViewHeightBasedOnChildren(Experience_List);*/

        textView_username = (TextView) findViewById(R.id.textView_username);
        textView_password = (TextView) findViewById(R.id.textView_password);

/*
        meAdapter = new EducationAdapter(ProfileActivity.this, mEducation);
        mexAdapter = new ExperienceAdapter(ProfileActivity.this, mExperience);

*/


/*

        Education_List.setAdapter(meAdapter);
        Experience_List.setAdapter(mexAdapter);
*/

        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            textView_username.setText(currentUser.getUsername());
            textView_password.setText(currentUser.getObjectId());

        } else {
            // show the signup or login screen
        }


        addMoreEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openEduAddDialog();


            }
        });


        addMoreExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openExpAddDialog();

            }
        });


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeIntent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(homeIntent);

            }
        });


        friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent homeIntent = new Intent(ProfileActivity.this,FriendListActivity.class);
                startActivity(homeIntent);

            }
        });


        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
                Intent homeIntent = new Intent(ProfileActivity.this,ChatActivity.class);
                startActivity(homeIntent);*/

            }
        });


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    private void pullEducation() {
        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseQuery<Education> query = ParseQuery.getQuery(Education.class);
            query.whereEqualTo("userId", currentUser.getObjectId());
            query.findInBackground(new FindCallback<Education>() {
                @Override
                public void done(List<Education> objects, ParseException e) {
                    Log.d("adapter_chk", "hoy ");
                    Log.d("adapter_chk", String.valueOf(objects.size()));
                    for (Education edu : objects) {
                        mEducation.add(edu);
                    }
//                    meAdapter.notifyDataSetChanged();
                    meReAdapter.notifyDataSetChanged();

                }
            });

        } else {
            // show the signup or login screen
        }

    }

    private void pullExperience() {
        final ParseUser currentUser = ParseUser.getCurrentUser();
        ParseQuery<Experience> query = ParseQuery.getQuery(Experience.class);
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.findInBackground(new FindCallback<Experience>() {
            @Override
            public void done(List<Experience> objects, ParseException e) {

                Log.d("adapter_chk", "hoy ");
                Log.d("adapter_chk", String.valueOf(objects.size()));
                for (Experience exp : objects) {
                    Log.d("adapter_chk", exp.getCompanyName().toString());
                    mExperience.add(exp);
                }
//                mexAdapter.notifyDataSetChanged();
                mexReAdapter.notifyDataSetChanged();

            }
        });

    }

    private void openExpAddDialog() {

        //check if there is a custom limit
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_add_experience, null);


        final LinearLayout jobendingLayout = (LinearLayout) alertLayout.findViewById(R.id.jobendingLayout);
        final EditText companyName = (EditText) alertLayout.findViewById(R.id.companyName);
        final EditText title = (EditText) alertLayout.findViewById(R.id.title);
        final EditText location = (EditText) alertLayout.findViewById(R.id.location);
        final EditText industry = (EditText) alertLayout.findViewById(R.id.industry);
        final EditText jobstartingDate = (EditText) alertLayout.findViewById(R.id.jobstartingDate);
        final CheckBox isCurrentJob = (CheckBox) alertLayout.findViewById(R.id.isCurrentJob);
        final EditText jobendingDate = (EditText) alertLayout.findViewById(R.id.jobendingDate);
        final EditText jobdescription = (EditText) alertLayout.findViewById(R.id.jobdescription);

        Button profile_category_save_button_job = (Button) alertLayout.findViewById(R.id.profile_category_save_button_job);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                currentJobStatus = true;


            }

        });


        isCurrentJob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isCurrentJob.isChecked()) {
//                    System.out.println("Checked");
                    jobendingLayout.setVisibility(View.GONE);
                    currentJobStatus = true;

                } else {
                    currentJobStatus = false;
                    jobendingLayout.setVisibility(View.VISIBLE);
//                    System.out.println("Un-Checked");
                }
            }
        });


        profile_category_save_button_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser currentUser = ParseUser.getCurrentUser();
//                    ParseObject experience = new ParseObject("Experience");
                Experience experience = new Experience();
                experience.put("userId", currentUser.getObjectId());
                experience.put("companyName", companyName.getText().toString());
                experience.put("title", title.getText().toString());
                experience.put("location", location.getText().toString());
                experience.put("industry", industry.getText().toString());
                experience.put("jobstartingDate", jobstartingDate.getText().toString());
                if (currentJobStatus) {
                    experience.put("isCurrentJob", "1");
                    experience.put("jobendingDate", "0");
                } else {
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
/*
                            Intent intent = new Intent(ProfileCategoryActivity.this, Locationactivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);*/

                            dialog.dismiss();
//                            mexAdapter.notifyDataSetChanged();
                            pullExperience();


                        }
                    }
                });

            }
        });

        dialog.show();
        int customwidth = width - 100;
        dialog.getWindow().setLayout(customwidth, 650);
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
//                            meAdapter.notifyDataSetChanged();
                            pullEducation();


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


    public void justifyListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMoreEducation:
                openEduAddDialog();
                break;
            case R.id.addMoreExperience:
                openExpAddDialog();
                break;

        }

    }
}
