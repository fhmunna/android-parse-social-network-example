package com.example.back4app.userregistrationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity implements FriendListAdapter.OnItemClick {

String st;
    RecyclerView recycler_friend_list;
    ArrayList<Friend> mFriend= new ArrayList<>();;
    FriendListAdapterT mFrindListAdapter;
    ArrayList<String> mFriendName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        recycler_friend_list=(RecyclerView) findViewById(R.id.friend_recycler_view);

      //  putFriends();


        mFrindListAdapter = new FriendListAdapterT(this, mFriendName);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_friend_list.setLayoutManager(layoutManager);
        recycler_friend_list.setItemAnimator(new DefaultItemAnimator());
        recycler_friend_list.setAdapter(mFrindListAdapter);




        pullFriends();
/*
        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);*/


    }

   private void putFriends(){
        ParseUser currentUser = ParseUser.getCurrentUser();

        Friend  friend= new Friend();
        friend.setFriendId("OnEJNsUbGI");
        friend.setUserId(currentUser.getObjectId());
        friend.setIsFriend(true);
        friend.saveInBackground();

        Friend  friend2= new Friend();
        friend2.setFriendId("z9T1t7ICBR");
        friend2.setUserId(currentUser.getObjectId());
        friend2.setIsFriend(true);
        friend2.saveInBackground();

   }


    private void pullFriends() {
        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
           ParseQuery<Friend> query = ParseQuery.getQuery(Friend.class);
            query.whereEqualTo("userId",currentUser.getObjectId().toString());
            query.findInBackground(new FindCallback<Friend>() {
                @Override
                public void done(List<Friend> objects, ParseException e) {
                    mFriend.clear();
                    Log.d("adapter_chk", "hoy hh");
                    Log.d("adapter_chk", String.valueOf(objects.size()));
                    for (Friend friend : objects) {

                        Log.d("adapter_chk", String.valueOf(friend.getFriendId().toString()));
                        getUsernameById(friend.getFriendId().toString());



                        mFriend.add(friend);
                    }
//                    meAdapter.notifyDataSetChanged();
//                    mFrindListAdapter.notifyDataSetChanged();

                }
            });

        } else {
            // show the signup or login screen
        }
    }

    @Override
    public void itemClick(int index) {
    }



    private void getUsernameById(String objectId) {
        Log.d("adapter_chk", objectId);


        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("objectId", objectId);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    // The query was successful.

                    Log.d("adapter_chk", String.valueOf(objects.size()));

                    for (ParseUser usr : objects) {

                        Log.d("adapter_chk", String.valueOf(usr.getUsername()));

//                        st= usr.getUsername();

                        mFriendName.add(usr.getUsername());



                    }


                } else {
                    // Something went wrong.
                    Log.d("adapter_chk", e.toString());
                }
            }
        });


        Log.d("adapter_chk00", String.valueOf(mFriendName.size()));

        mFrindListAdapter.notifyDataSetChanged();


    }

    public void profileLoader(String s) {




    }
}
