package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder> {
    private List<String> mFriend;
    private Context mContext;
    String st;
    private  OnItemClick mOnItemClick;

    public FriendListAdapter(Context context, List<String> friends,OnItemClick onItemClick ) {
        this.mContext = context;
        this.mFriend = friends;
        this.mOnItemClick=onItemClick;
    }


    public class FriendListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text_view_friend;

        public FriendListViewHolder(View view) {
            super(view);

            text_view_friend = (TextView) view.findViewById(R.id.text_view_friend);
        }

        @Override
        public void onClick(View view) {
            mOnItemClick.itemClick(getAdapterPosition());
        }
    }




    @Override
    public FriendListAdapter.FriendListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new FriendListAdapter.FriendListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final FriendListViewHolder holder, int position) {
        final String friend = mFriend.get(position);



                    holder.text_view_friend.setText(friend);








    }


    @Override
    public int getItemCount() {
        return mFriend.size();
    }

    interface OnItemClick{
         void itemClick(int index);
    }






}
