package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class EducationReAdaptere extends RecyclerView.Adapter<EducationReAdaptere.EducationReAdaptereViewHolder> {

    private List<Education> meducation;
    int itemIndex = 0;
    private Context mContext;


    public class EducationReAdaptereViewHolder extends RecyclerView.ViewHolder {
        public TextView institutionName;
        public TextView degree;

        public EducationReAdaptereViewHolder(View view) {
            super(view);

            institutionName = (TextView) view.findViewById(R.id.institutionName);
            degree = (TextView) view.findViewById(R.id.degree);


        }
    }


    public EducationReAdaptere(Context context, List<Education> education) {
        this.mContext = context;
        this.meducation = education;

    }

    @Override
    public EducationReAdaptereViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.education_item, parent, false);
        return new EducationReAdaptereViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final EducationReAdaptereViewHolder holder, final int position) {
        final Education education = meducation.get(position);
        holder.institutionName.setText(education.getInstitutionName());
        holder.degree.setText(education.getDegree());


    }

    @Override
    public int getItemCount() {
        return meducation.size();
    }
}
