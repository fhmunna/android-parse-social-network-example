package com.example.back4app.userregistrationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class FullPostActivity extends AppCompatActivity {
    String key_object_id;
    TextView postBody;
    TextView postStat;
    ImageButton likeButton;
    String postObjectId;
    boolean like_exist;
    Post postStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);
        postBody = (TextView) findViewById(R.id.postBody);
        postStat = (TextView) findViewById(R.id.postStat);
        likeButton = (ImageButton) findViewById(R.id.like);
        Bundle bundle = getIntent().getExtras();
        key_object_id = bundle.getString("key_object_id");

        postStorage = new Post();
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
                        postObjectId = post.getObjectId();

                        postStorage = post;

                    }
//                    meAdapter.notifyDataSetChanged();
//                    mPostAdapter.notifyDataSetChanged();

                }
            });

        } else {
            // show the signup or login screen
        }
        updateLikeCount();

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
checkLike();
/*
                if (!checkLike()) {
Log.d("chk_like","not found");

                    final ParseUser currentUser = ParseUser.getCurrentUser();

                    Like like = new Like();
                    like.put("likeAuthor", currentUser.getObjectId());
                    like.put("likeRefference", key_object_id);

                    like.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                updateLikeCount();
                            }
                        }
                    });


                } else {
                    Log.d("chk_like","found");

                    clearLike();
                }*/


            }
        });


    }

    private void checkLike() {
//        like_exist = false;
        final ParseUser currentUser = ParseUser.getCurrentUser();


        ParseQuery<Like> query = ParseQuery.getQuery(Like.class);
        query.whereEqualTo("likeAuthor", currentUser.getObjectId().toString());
        query.findInBackground(new FindCallback<Like>() {
            @Override
            public void done(List<Like> objects, ParseException e) {

                int likeCount = objects.size();
//                postStat.setText(String.valueOf(likeCount));
                if (likeCount == 0) {
                    addLike();

                }else {
                    clearLike();
                }

            }
        });
//        return like_exist;
    }

    public void clearLike() {

        final ParseUser currentUser = ParseUser.getCurrentUser();


        ParseQuery<Like> queryAuthor = ParseQuery.getQuery(Like.class);
        queryAuthor.whereEqualTo("likeAuthor", currentUser.getObjectId().toString());

        ParseQuery<Like> queryLike = ParseQuery.getQuery(Like.class);
        queryLike.whereEqualTo("likeRefference", key_object_id);


        List<ParseQuery<Like>> queries = new ArrayList<ParseQuery<Like>>();
        queries.add(queryAuthor);
        queries.add(queryLike);

        ParseQuery<Like> mainQuery = ParseQuery.or(queries);

        mainQuery.findInBackground(new FindCallback<Like>() {
            public void done(List<Like> results, ParseException e) {
                // results has the list of players that win a lot or haven't won much.


                for (Like like : results) {
                    like.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {

                            updateLikeCount();
                        }
                    });

                }
            }
        });


    }


    public void addLike(){

        final ParseUser currentUser = ParseUser.getCurrentUser();

        Like like = new Like();
        like.put("likeAuthor", currentUser.getObjectId());
        like.put("likeRefference", key_object_id);

        like.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    updateLikeCount();
                }
            }
        });
    }
    private void updateLikeCount() {

        ParseQuery<Like> query = ParseQuery.getQuery(Like.class);
        query.whereEqualTo("likeRefference", key_object_id);
        query.findInBackground(new FindCallback<Like>() {
            @Override
            public void done(List<Like> objects, ParseException e) {

                int likeCount = objects.size();
                postStat.setText(String.valueOf(likeCount));


            }
        });


    }
}
