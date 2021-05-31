package com.example.formula1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private final LinkedList<String> commentList;
    private LayoutInflater mInflater;

    public CommentAdapter(Context context,
                          LinkedList<String> wordList){
        mInflater = LayoutInflater.from(context);
        this.commentList = wordList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.comment_item,
                parent, false);
        return new CommentAdapter.CommentViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        String mCurrent = commentList.get(position);
        holder.commentItemView.setText(" (#"+(position+1)+") "+mCurrent);

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public final TextView commentItemView;
        final CommentAdapter mAdapter;

        public CommentViewHolder(View itemView, CommentAdapter adapter) {
            super(itemView);
            commentItemView = itemView.findViewById(R.id.commentTextViewRow);
            this.mAdapter = adapter;
        }
    }
}
