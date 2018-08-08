package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 7/22/2018.
 */


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> mComment;
    int itemIndex = 0;
    private Context mContext;


    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView commentBody;


        public CommentViewHolder(View view) {
            super(view);

            commentBody = (TextView) view.findViewById(R.id.commentBody);



        }
    }


    public CommentAdapter(Context context, List<Comment> comments) {
        this.mContext = context;
        this.mComment = comments;

    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, final int position) {
        final Comment comment = mComment.get(position);
        holder.commentBody.setText(comment.getCommentContent());



    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }
}
