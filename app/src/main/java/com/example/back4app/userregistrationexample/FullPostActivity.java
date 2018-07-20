package com.example.back4app.userregistrationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class FullPostActivity extends AppCompatActivity {
    String key_object_id;
    TextView postBody;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);
        postBody = (TextView)findViewById(R.id.postBody);
        Bundle bundle = getIntent().getExtras();
         key_object_id = bundle.getString("key_object_id");


        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
            query.whereEqualTo("objectId", key_object_id);
            query.findInBackground(new FindCallback<Post>() {
                @Override
                public void done(List<Post> objects, ParseException e) {
//                    mPostList.clear();
                    Log.d("adapter_chk", "hoy ");
                    Log.d("adapter_chk", String.valueOf(objects.size()));
                    for (Post post : objects) {
//                        mPostList.add(post);
                        postBody.setText(post.getBody());

                    }
//                    meAdapter.notifyDataSetChanged();
//                    mPostAdapter.notifyDataSetChanged();

                }
            });

        } else {
            // show the signup or login screen
        }






    }
}
