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
//    String monthName;

    public class EducationReAdaptereViewHolder extends RecyclerView.ViewHolder {
/*        public TextView textviewDateNumber, textviewDayName;
        public LinearLayout layoutItemCalender;*/

        public TextView institutionName;
        public TextView degree;

        public EducationReAdaptereViewHolder(View view) {
            super(view);
/*            textviewDateNumber = (TextView) view.findViewById(R.id.textview_date_number);
            textviewDayName = (TextView) view.findViewById(R.id.textview_day_name);
            layoutItemCalender = (LinearLayout) view.findViewById(R.id.layout_item_calender);*/

            institutionName = (TextView)view.findViewById(R.id.institutionName);
            degree = (TextView) view.findViewById(R.id.degree);


        }
    }


    public EducationReAdaptere(Context context, List<Education> education) {
        this.mContext = context;
        this.meducation = education;
/*        this.dateDoctorPatients = dateDoctorPatients;
        this.monthName = monthName;*/
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


/*
        holder.layoutItemCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemIndex = position;
                int[] location = new int[2];
                int top = holder.layoutItemCalender.getTop();
                Log.d("llooccaattiioonn",String.valueOf(itemIndex));
                if (mContext instanceof DoctorHomePageActivity) {
                    ((DoctorHomePageActivity) mContext).getdatefromsideBar(dateDoctorPatient.getmDateNumber(), dateDoctorPatient.getmDayName(),dateDoctorPatient.getmDateMonth(),top,itemIndex);

                }
                notifyDataSetChanged();
            }

        });
*/

/*        if (position == 0) {
            holder.textviewDateNumber.setTextColor(Color.parseColor("#FFFFFF"));
            holder.textviewDayName.setTextColor(Color.parseColor("#FFFFFF"));
        }

        if (itemIndex == position) {
            holder.textviewDateNumber.setTextColor(Color.parseColor("#FFFFFF"));
            holder.textviewDayName.setTextColor(Color.parseColor("#FFFFFF"));

        } else {
            holder.textviewDateNumber.setTextColor(Color.parseColor("#7B888E"));
            holder.textviewDayName.setTextColor(Color.parseColor("#7B888E"));
        }*/



    }

    @Override
    public int getItemCount() {
        return meducation.size();
    }
}
