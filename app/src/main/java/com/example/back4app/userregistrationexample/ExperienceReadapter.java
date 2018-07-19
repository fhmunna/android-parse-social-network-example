package com.example.back4app.userregistrationexample;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ExperienceReadapter extends RecyclerView.Adapter<ExperienceReadapter.ExperienceReadapterViewHolder> {

    private List<Experience> mExperience;
    int itemIndex = 0;
    private Context mContext;


    public class ExperienceReadapterViewHolder extends RecyclerView.ViewHolder {
        public TextView companyName;
        public TextView title;

        public ExperienceReadapterViewHolder(View view) {
            super(view);

            companyName = (TextView) view.findViewById(R.id.companyName);
            title = (TextView) view.findViewById(R.id.title);


        }
    }


    public ExperienceReadapter(Context context, List<Experience> experience) {
        this.mContext = context;
        this.mExperience = experience;

    }

    @Override
    public ExperienceReadapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experience_item, parent, false);
        return new ExperienceReadapterViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ExperienceReadapterViewHolder holder, final int position) {
        final Experience experience = mExperience.get(position);
        holder.companyName.setText(experience.getCompanyName());
        holder.title.setText(experience.getTitle());
    }

    @Override
    public int getItemCount() {
        return mExperience.size();
    }
}


