package com.example.formula1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.WordViewHolder> implements Filterable {

    private final ArrayList<Topic> topics;
    private LayoutInflater mInflater;
    Context contexto;
    private static List<Topic> filter_topics;
    static int lastPos = 0;

    public TopicAdapter(Context context,
                        ArrayList<Topic> topic) {
        contexto = context;
        this.topics = topic;
        this.filter_topics = new ArrayList<>(topic);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        mInflater = LayoutInflater.from(contexto);
        View mItemView = mInflater.inflate(R.layout.topic,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String mCurrent = filter_topics.get(position).getTitle();
        holder.wordItemView.setText(mCurrent);
        holder.keywordView.setText(filter_topics.get(position).getKeyword());
        holder.numCommentView.setText("Comments:"+filter_topics.get(position).getComments().size());
        holder.constlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPos = position;
                Intent intent = new Intent(contexto, TopicComments.class);
                intent.putExtra("desc",filter_topics.get(position).getDescription());
                contexto.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filter_topics.size();
    }

    @Override
    public Filter getFilter() {
        return topicFilter;
    }

    private final Filter topicFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Topic> filterd_list = new ArrayList<>();

            if(constraint == null){
                filterd_list.addAll(topics);
            }else{
                String filter_query = constraint.toString().toLowerCase().trim();
                for(Topic topic: topics){
                    if(topic.getKeyword().toLowerCase().contains(filter_query) || topic.getTitle().toLowerCase().contains(filter_query))
                        filterd_list.add(topic);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterd_list;
            results.count = filterd_list.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filter_topics.clear();
            filter_topics.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        public final TextView keywordView;
        public final TextView numCommentView;
        public final ConstraintLayout constlayout;
        final TopicAdapter mAdapter;

        public WordViewHolder(View itemView, TopicAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.wordTextView);
            keywordView = itemView.findViewById(R.id.keywordView);
            constlayout = itemView.findViewById(R.id.linearLayout);
            numCommentView = itemView.findViewById(R.id.numcomments);
            this.mAdapter = adapter;
        }
    }

    public static int givepos(){
        return lastPos;
    }

    public static Topic givefilteredObject() {
        return filter_topics.get(TopicAdapter.givepos());
    }


}