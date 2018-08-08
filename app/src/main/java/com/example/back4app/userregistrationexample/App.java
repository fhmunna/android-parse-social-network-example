package com.example.back4app.userregistrationexample;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

public class App extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }
    public static final String YOUR_APPLICATION_ID = "QN0K6MGBw2GOXPdfZSnJuFYRk1gYVCBSIaTl6tFP";
    public static final String YOUR_CLIENT_KEY = "kgDhMbCkZwuOX03ES2wPzA48JxrfhoAXDvDJpULh";

    @Override
    public void onCreate() {
        super.onCreate();

            ParseObject.registerSubclass(Education.class);
            ParseObject.registerSubclass(Experience.class);
            ParseObject.registerSubclass(Post.class);
            ParseObject.registerSubclass(Like.class);
            ParseObject.registerSubclass(Comment.class);
            Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                    .applicationId("QN0K6MGBw2GOXPdfZSnJuFYRk1gYVCBSIaTl6tFP")
                    .clientKey("kgDhMbCkZwuOX03ES2wPzA48JxrfhoAXDvDJpULh")
                    .server(" https://parseapi.back4app.com/")
                    .build());
            //Parse.enableLocalDatastore(this);
//        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

            ParseInstallation.getCurrentInstallation().saveInBackground();




        }


}
