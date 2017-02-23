package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
                if (isFormat())
                {
                    String username = inputLayout_username.getEditText().getText().toString();
                    String password = inputLayout_password.getEditText().getText().toString();
                    Log.i(TAG, "monitor: username: " + username);
                    Log.i(TAG, "monitor: password: " + password);
                }
            }
        });
        setError(inputLayout_username);
        setError(inputLayout_password);
    }

    @SuppressWarnings("ConstantConditions")
    private boolean isFormat()
    {
        boolean result = true;
        if (inputLayout_username.getEditText().getText().length() == 0)
        {
            inputLayout_username.setError(getString(R.string.error_null_text));
            result = false;
        }
        if (inputLayout_password.getEditText().getText().length() == 0)
        {
            inputLayout_password.setError(getString(R.string.error_null_text));
            result = false;
        }
        return result;
    }

    @SuppressWarnings("ConstantConditions")
    private void setError(final TextInputLayout inputLayout)
    {
        inputLayout.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (editable.length() == 0)
                {
                    inputLayout.setError(getString(R.string.error_null_text));
                } else
                {
                    inputLayout.setError(null);
                }
            }
        });
    }
}
