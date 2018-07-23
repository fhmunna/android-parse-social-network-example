package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class FriendListAdapterT extends RecyclerView.Adapter<FriendListAdapterT.FriendListViewHolder> {
    private List<String> mFriendName;
    private Context mContext;

    public class FriendListViewHolder extends RecyclerView.ViewHolder {
        public TextView text_view_friend;
        LinearLayout lay_fri;
//        public TextView authorName;

        public FriendListViewHolder(View view) {
            super(view);

            text_view_friend = (TextView) view.findViewById(R.id.text_view_friend);
            lay_fri = (LinearLayout) view.findViewById(R.id.layout_post_item);
//            authorName = (TextView) view.findViewById(R.id.authorName);


        }
    }


    public FriendListAdapterT(Context context, List<String> post) {
        this.mContext = context;
        this.mFriendName = post;
    }


    @Override
    public FriendListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new FriendListViewHolder(itemView);

    }



    @Override
    public void onBindViewHolder(final FriendListViewHolder holder, final int position) {
        final String post = mFriendName.get(position);
        holder.text_view_friend.setText(post);

        holder.lay_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mContext instanceof PostActivity){
                    ((FriendListActivity)mContext).profileLoader(mFriendName.get(position).toString());

                }

            }
        });
//        holder.au.setText(experience.getTitle());
    }


    @Override
    public int getItemCount() {
        return mFriendName.size();
    }
}

