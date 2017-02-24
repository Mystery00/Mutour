package com.weily.mutour.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.weily.mutour.R;

public class SignInActivity extends AppCompatActivity
{
    private static final String TAG = "SignInActivity";
    private static final int REQUEST_CODE = 2056;
    private View view;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextInputLayout inputLayout_username;
    private TextInputLayout inputLayout_password;
    private TextView text_new_account;

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
        view = findViewById(R.id.coordinatorLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        inputLayout_username = (TextInputLayout) findViewById(R.id.username_text_input);
        inputLayout_password = (TextInputLayout) findViewById(R.id.password_text_input);
        text_new_account = (TextView) findViewById(R.id.new_account);

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
        setError(inputLayout_username);
        setError(inputLayout_password);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });
        text_new_account.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(new Intent(SignInActivity.this, SignUpActivity.class), REQUEST_CODE);
            }
        });
        //软键盘右下角按钮监听
        inputLayout_password.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                login();
                return false;
            }
        });
    }

    @SuppressWarnings("ConstantConditions")
    private boolean isFormat()
    {
        return inputLayout_username.getEditText().getText().length() != 0
                && inputLayout_password.getEditText().getText().length() >= 6;
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
                    if (inputLayout.equals(inputLayout_username))
                    {
                        inputLayout.setError(getString(R.string.error_null_username));
                    } else
                    {
                        inputLayout.setError(getString(R.string.error_null_password));
                    }
                } else if (editable.length() < 6)
                {
                    if (inputLayout.equals(inputLayout_password))
                    {
                        inputLayout.setError(getString(R.string.error_short_password));
                    }
                } else
                {
                    inputLayout.setError(null);
                }
            }
        });
    }

    @SuppressWarnings("ConstantConditions")
    private void login()
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (isFormat())
        {
            String username = inputLayout_username.getEditText().getText().toString();
            String password = inputLayout_password.getEditText().getText().toString();
            Log.i(TAG, "monitor: username: " + username);
            Log.i(TAG, "monitor: password: " + password);
        } else
        {
            Snackbar.make(view, R.string.error_login_null, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            inputLayout_username.getEditText().setText(data.getStringExtra("username"));
            inputLayout_password.getEditText().setText(data.getStringExtra("password"));
        }
    }
}
