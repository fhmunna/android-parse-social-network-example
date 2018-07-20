package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExperienceAdapter extends ArrayAdapter<Experience> {
    public ExperienceAdapter(@NonNull Context context, List<Experience> experience) {
        super(context, 0, experience);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.experience_item, parent, false);
            final ExperienceAdapter.ViewHolder holder = new ExperienceAdapter.ViewHolder();
            holder.companyName = (TextView) convertView.findViewById(R.id.companyName);
            holder.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        }
        final Experience experience = (Experience) getItem(position);
        final ExperienceAdapter.ViewHolder holder = (ExperienceAdapter.ViewHolder) convertView.getTag();


        holder.title.setText(experience.getTitle());
        holder.companyName.setText(experience.getCompanyName());


        Log.d("adapter_chk", experience.getCompanyName().toString());
        return convertView;


    }


    final class ViewHolder {
        public TextView companyName;
        public TextView title;
    }


}
