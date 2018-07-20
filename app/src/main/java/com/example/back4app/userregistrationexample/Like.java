package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ASUS on 7/20/2018.
 */

@ParseClassName("Like")
public class Like extends ParseObject {
    public String getLikeRefference(){return getString("likeRefference");}
    public String getLiketAuthor(){return getString("likeAuthor");}
    public void setLikeRefference(String likeRefference){put("likeRefference",likeRefference);}
    public void setLikeAuthor(String likeAuthor){put("likeAuthor",likeAuthor);}
}
