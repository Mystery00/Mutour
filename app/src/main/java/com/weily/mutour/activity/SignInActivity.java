package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.weily.mutour.R;

public class SignInActivity extends AppCompatActivity
{
    private static final String TAG = "SignInActivity";
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextInputLayout inputLayout_username;
    private TextInputLayout inputLayout_password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initialize();
        monitor();
    }

    private void initialize()
    {
        setContentView(R.layout.activity_sign_in);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        inputLayout_username = (TextInputLayout) findViewById(R.id.username_text_input);
        inputLayout_password = (TextInputLayout) findViewById(R.id.password_text_input);

        setSupportActionBar(toolbar);
    }

    @SuppressWarnings("ConstantConditions")
    private void monitor()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = inputLayout_username.getEditText().getText().toString();
                String password = inputLayout_password.getEditText().getText().toString();
                Log.i(TAG, "monitor: username: " + username);
                Log.i(TAG, "monitor: password: " + password);
            }
        });
    }

}
