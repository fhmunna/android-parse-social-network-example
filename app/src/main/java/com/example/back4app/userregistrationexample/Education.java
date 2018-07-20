package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("Education")
public class Education extends ParseObject {
    public String getInstitutionName(){return getString("institutionName");}
    public String getDegree(){return getString("degree");}
    public String getStartingDate(){return getString("startingDate");}
    public String getIsCurrent(){return getString("isCurrent");}
    public String getEndingdate(){return getString("endingDate");}
    public String getFieldOfStudy(){return getString("fieldOfStudy");}
    public String getDescription(){return getString("description");}
    public String getUserId(){return getString("userId");}


    public void setInstitutionName(String institutionName){ put("institutionName", institutionName);}
    public void setDegree(String degree){ put("degree", degree);}
    public void setStartingDate(String startingDate){ put("startingDate", startingDate);}
    public void setIsCurrent(String isCurrent){ put("isCurrent", isCurrent);}
    public void setEndingdate(String endingDate){ put("endingDate", endingDate);}
    public void setFieldOfStudy(String fieldOfStudy){ put("fieldOfStudy", fieldOfStudy);}
    public void setDescription(String description){ put("description", description);}
    public void setUserId(String userId){ put("userId", userId);}

}
