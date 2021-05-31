package com.example.formula1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Forum extends Fragment {

    private static final ArrayList<Topic> topics = new ArrayList<>();
    Topic topic;
    public static final int TEXT_REQUEST = 1;
    private RecyclerView mRecyclerView;
    private TopicAdapter mAdapter;
    Context context;
    private SearchView search_topic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        setUpToolbar(view);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new TopicAdapter(context, topics);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        FloatingActionButton fabbutton = view.findViewById(R.id.floatingActionButton5);
        fabbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTopic.class);
                startActivityForResult(intent,TEXT_REQUEST);
            }
        });

        return view;

    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.search_menu);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.forum_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem search_race_item = menu.findItem(R.id.search_topic);

        search_race_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        search_topic = (SearchView) search_race_item.getActionView();
        search_topic.setMaxWidth(Integer.MAX_VALUE);
        search_topic.setQueryHint("Search Topic");

        search_topic.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            String replyTitle = data.getStringExtra(AddTopic.title);
            String replyDesc = data.getStringExtra(AddTopic.desc);
            String replyKeyword = data.getStringExtra(AddTopic.keyword);
            topic = new Topic(replyTitle,replyDesc,replyKeyword);
            topics.add(0,topic);
            mAdapter = new TopicAdapter(context, topics);
            // Connect the adapter with the RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public static Topic giveObject() {
        return topics.get(TopicAdapter.givepos());
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new TopicAdapter(context, topics);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
    }

}