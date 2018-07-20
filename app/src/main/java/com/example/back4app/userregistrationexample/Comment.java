package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ASUS on 7/20/2018.
 */

@ParseClassName("Comment")
public class Comment extends ParseObject {
    public String getCommentRefference(){return getString("commentRefference");}
    public String getCommentAuthor(){return getString("commentAuthor");}
    public String getCommentContent(){return getString("commentContent");}



    public void setCommentRefference(String commentRefference){put("commentRefference",commentRefference);}
    public void setCommentAuthor(String commentAuthor){put("commentAuthor",commentAuthor);}
    public void setCommentContent(String commentContent){put("commentContent",commentContent);}



}
