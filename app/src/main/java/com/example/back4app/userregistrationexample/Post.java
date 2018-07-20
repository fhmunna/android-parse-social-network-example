package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;



@ParseClassName("Post")
public class Post extends ParseObject {
    public String getAuthorId(){return getString("userId");}
    public String getBody(){return getString("body");}
    public String getMetadata(){return getString("metadata");}
    public String getThumbURl(){return getString("thumbUrl");}



    public void setAuthorId(String userId){put("userId",userId);}
    public void setBody(String body){put("userId",body);}
    public void setMetadata(String metadata){put("userId",metadata);}
    public void setThumbURl(String thumbUrl){put("userId",thumbUrl);}








}
