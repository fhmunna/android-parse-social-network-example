package com.example.back4app.userregistrationexample;

import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("Experience")
public class Experience extends ParseObject {
    public String getUserId(){return getString("userId");}
    public String getCompanyName(){return getString("companyName");}
    public String getIndustry(){return getString("industry");}
    public String getJobStartingdate(){return getString("jobstartingDate");}
    public String getIsCurrentJob(){return getString("isCurrentJob");}
    public String getJobEndingdate(){return getString("jobendingDate");}
    public String getLocation(){return getString("location");}
    public String getTitle(){return getString("title");}
    public String getJobDescription(){return getString("jobdescription");}



    public void setUserId(String userId){put("userId",userId);}
    public void setCompanyName(String companyName){put("companyName",companyName);}
    public void setIndustry(String industry){put("industry",industry);}
    public void setJobStartingdate(String jobstartingDate){put("jobstartingDate",jobstartingDate);}
    public void setIsCurrentJob(String isCurrentJob){put("isCurrentJob",isCurrentJob);}
    public void setJobEndingdate(String jobendingDate){put("jobendingDate",jobendingDate);}
    public void setLocation(String location){put("location",location);}
    public void setTitle(String title){put("title",title);}
    public void setJobDescription(String jobdescription){put("jobdescription",jobdescription);}

}
