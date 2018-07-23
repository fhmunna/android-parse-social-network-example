package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Rating")
public class Rating  extends ParseObject{
    public String getRatingReciever(){return getString("ratingReciever");}
    public String getRatingProvider(){return getString("RatingProvider");}
    public String getRatingValue(){return getString("RatingValue");}



    public void setRatingReference(String ratingReciever){
        put("ratingReciever",ratingReciever);
    }
    public void setRatingProvider(String RatingProvider){
        put("RatingProvider",RatingProvider);}
    public void setRatingValue(String RatingProvider){
        put("RatingProvider",RatingProvider);
    }

}


/*
*
* @ParseClassName("Comment")
public class Comment extends ParseObject {
    public String getCommentRefference(){return getString("commentRefference");}
    public String getCommentAuthor(){return getString("commentAuthor");}
    public String getCommentContent(){return getString("commentContent");}



    public void setCommentRefference(String commentRefference){put("commentRefference",commentRefference);}
    public void setCommentAuthor(String commentAuthor){put("commentAuthor",commentAuthor);}
    public void setCommentContent(String commentContent){put("commentContent",commentContent);}



}

*
*
* */
