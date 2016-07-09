package com.bizagi.app.workportal.inbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bizagi.app.workportal.R;

public class InboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        setTitle(getString(R.string.inbox_title));
    }
}
