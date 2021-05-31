package com.example.formula1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddTopic extends AppCompatActivity {

    public static final String title =
            "title";
    public static final String desc =
            "description";
    public static final String keyword =
            "keyword";
    private EditText mTitle;
    private EditText mDesc;
    private EditText mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_topic);
        mTitle = findViewById(R.id.editTextTitle);
        mDesc = findViewById(R.id.editTextDescription);
        mKey = findViewById(R.id.editTextKeyword);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void returnReply(View view) {
        String reply = mTitle.getText().toString();
        String descr = mDesc.getText().toString();
        String keyw = mKey.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(title, reply);
        replyIntent.putExtra(desc,descr);
        replyIntent.putExtra(keyword,keyw);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void cancel(View view) {
        finish();
    }
}