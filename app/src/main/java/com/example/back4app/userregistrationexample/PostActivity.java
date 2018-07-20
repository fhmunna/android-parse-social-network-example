package com.example.back4app.userregistrationexample;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    RecyclerView recycler_post_list;
    ArrayList<Post> mPostList;
    PostAdapter mPostAdapter;

    Button addPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        addPost = (Button) findViewById(R.id.addPost);

        recycler_post_list = (RecyclerView) findViewById(R.id.recycler_post_list);

        mPostList = new ArrayList<Post>();

        pullPost();

        mPostAdapter = new PostAdapter(this, mPostList);
        recycler_post_list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_post_list.setLayoutManager(layoutManager);
        recycler_post_list.setAdapter(mPostAdapter);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPostAddDialog();

            }
        });


    }

    private void pullPost() {
        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
            query.whereEqualTo("userId", currentUser.getObjectId());
            query.findInBackground(new FindCallback<Post>() {
                @Override
                public void done(List<Post> objects, ParseException e) {
                    Log.d("adapter_chk", "hoy ");
                    Log.d("adapter_chk", String.valueOf(objects.size()));
                    for (Post post : objects) {
                        mPostList.add(post);
                    }
//                    meAdapter.notifyDataSetChanged();
                    mPostAdapter.notifyDataSetChanged();

                }
            });

        } else {
            // show the signup or login screen
        }


    }

    private void openPostAddDialog() {
        //check if there is a custom limit
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_add_post, null);

        final EditText thePost = (EditText) alertLayout.findViewById(R.id.thePost);
        Button post_save_button = (Button) alertLayout.findViewById(R.id.post_save_button);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

            }
        });


        post_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseUser currentUser = ParseUser.getCurrentUser();
                Post post = new Post();
                post.put("userId", currentUser.getObjectId());
                post.put("body", thePost.getText().toString());
                post.put("metadata", "dummy");
                post.put("thumbUrl", "dummy");
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            dialog.dismiss();
                            pullPost();
                        }
                    }
                });
            }
        });
        dialog.show();
    }
}
