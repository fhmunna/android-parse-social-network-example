package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("Friend")
public class Friend extends ParseObject {

    public String getUserId(){return getString("userId");}
    public String getFriendId(){return getString("friendId");}
    public String getIsFriend(){return getString("isFriend");}



    public void setUserId(String userId){put("userId",userId);}
    public void setFriendId(String friendId){put("friendId",friendId);}
    public void setIsFriend(Boolean isFriend){put("isFriend",isFriend);}
}
