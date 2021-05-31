package com.example.formula1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TopicComments extends AppCompatActivity {
    private TextView description;
    private TextView num_comments;
    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;

    Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.topic_comments);
        description = findViewById(R.id.Desc);
        description.setMovementMethod(new ScrollingMovementMethod());
        description.setText(getIntent().getStringExtra("desc"));
        topic = Forum.giveObject();
        num_comments = findViewById(R.id.numbercomments);


        mRecyclerView = findViewById(R.id.recyclerviewcomments);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new CommentAdapter(this, topic.getComments());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        num_comments.setText("Number of current comments:"+topic.getComments().size());
    }

    public void updatelist(View view) {
        EditText text;
        text = findViewById(R.id.editTextTextPersonName);
        topic.addComments(text.getText().toString());
        text.setText("");
        // Create an adapter and supply the data to be displayed.
        mAdapter = new CommentAdapter(this, topic.getComments());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        num_comments.setText("Number of current comments:"+topic.getComments().size());
    }

    public void goBack(View view){
        finish();
    }

}

