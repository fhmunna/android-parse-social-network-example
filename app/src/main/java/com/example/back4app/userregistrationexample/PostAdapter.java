package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> mPost;
    private Context mContext;

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView postBody;
        LinearLayout layout_post_item;
//        public TextView authorName;

        public PostViewHolder(View view) {
            super(view);

            postBody = (TextView) view.findViewById(R.id.postBody);
            layout_post_item = (LinearLayout) view.findViewById(R.id.layout_post_item);
//            authorName = (TextView) view.findViewById(R.id.authorName);


        }
    }


    public PostAdapter(Context context, List<Post> post) {
        this.mContext = context;
        this.mPost = post;
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(itemView);

    }



    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {
        final Post post = mPost.get(position);
        holder.postBody.setText(post.getBody());
        holder.layout_post_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mContext instanceof PostActivity){
                    ((PostActivity)mContext).postLoader(mPost.get(position).getObjectId().toString());

                }

            }
        });
//        holder.au.setText(experience.getTitle());
    }


    @Override
    public int getItemCount() {
        return mPost.size();
    }
}

