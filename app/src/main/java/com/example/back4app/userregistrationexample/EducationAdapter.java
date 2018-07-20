package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EducationAdapter extends ArrayAdapter<Education> {
    private String mUserId;

    public EducationAdapter(@NonNull Context context, List<Education> education) {
        super(context, 0, education);
//        this.mUserId = userId;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.education_item, parent, false);
            final ViewHolder holder = new ViewHolder();
            holder.institutionName = (TextView)convertView.findViewById(R.id.institutionName);
            holder.degree = (TextView)convertView.findViewById(R.id.degree);
//            holder.body = (TextView)convertView.findViewById(R.id.tvBody);
            convertView.setTag(holder);
        }
        final Education education = (Education) getItem(position);
        final ViewHolder holder = (ViewHolder)convertView.getTag();
        final boolean isMe = education.getUserId().equals(mUserId);
        // Show-hide image based on the logged-in user.
        // Display the profile image to the right for our user, left for other users.
        /*if (isMe) {
            holder.imageRight.setVisibility(View.VISIBLE);
            holder.imageLeft.setVisibility(View.GONE);
            holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        } else {
            holder.imageLeft.setVisibility(View.VISIBLE);
            holder.imageRight.setVisibility(View.GONE);
            holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        }*/
        /*final ImageView profileView = isMe ? holder.imageRight : holder.imageLeft;
        Picasso.with(getContext()).load(getProfileUrl(message.getUserId())).into(profileView);
        holder.body.setText(message.getBody());
        */

        holder.degree.setText(education.getDegree());
        holder.institutionName.setText(education.getInstitutionName());


        Log.d("adapter_chk",education.getInstitutionName().toString());
        return convertView;


    }


    final class ViewHolder {
        public TextView institutionName;
        public TextView degree;
    }



}
