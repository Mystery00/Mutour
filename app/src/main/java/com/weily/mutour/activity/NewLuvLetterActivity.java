package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mystery0.tools.Logs.Logs;
import com.weily.mutour.R;
import com.weily.mutour.adapter.NewLuvLetterAdapter;
import com.weily.mutour.callback.NewLuvLetterListener;

public class NewLuvLetterActivity extends AppCompatActivity implements NewLuvLetterListener
{
    private static final String TAG = "NewLuvLetterActivity";
    private Toolbar toolbar;
    private RelativeLayout content;
    private View coordinatorLayout;
    private EditText edit_luv_text;
    private EditText edit_luv_sender;
    private CheckBox isAnonymity;
    private int selected_color;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //checkPermission();
        initialize();
        monitor();
    }

    private void initialize()
    {
        setContentView(R.layout.activity_new_luv_letter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edit_luv_text = (EditText) findViewById(R.id.new_luv_letter_text);
        edit_luv_sender = (EditText) findViewById(R.id.new_luv_letter_sender);
        isAnonymity = (CheckBox) findViewById(R.id.checkBox);
        content = (RelativeLayout) findViewById(R.id.content_new_luv_letter);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.new_luv_letter_selector);
        recyclerView.setLayoutManager(new GridLayoutManager(NewLuvLetterActivity.this, 5));
        recyclerView.setAdapter(new NewLuvLetterAdapter(this));

        selected_color = R.color.color_deep_purple;
        content.setBackgroundResource(selected_color);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.new_luv_letter, menu);
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_done:
                if (edit_luv_text.getText().toString().length() == 0)
                {
                    Snackbar.make(coordinatorLayout, getString(R.string.error_null_text), Snackbar.LENGTH_SHORT)
                            .show();
                    break;
                }
                if ((edit_luv_sender.getText().toString().length() == 0 && isAnonymity.isChecked()) ||
                        edit_luv_sender.getText().toString().length() != 0)
                {
                    String text = edit_luv_text.getText().toString();
                    String sender = getString(R.string.hint_new_luv_letter_anonymity);
                    int color = selected_color;
                    if (!isAnonymity.isChecked())
                    {
                        sender = edit_luv_sender.getText().toString();
                    }
                    Logs.i(TAG, "onOptionsItemSelected: text: " + text);
                    Logs.i(TAG, "onOptionsItemSelected: sender: " + sender);
                    Logs.i(TAG, "onOptionsItemSelected: color: " + color);
                    //done
                    finish();
                } else
                {
                    Snackbar.make(coordinatorLayout, getString(R.string.error_null_sender), Snackbar.LENGTH_SHORT)
                            .show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemSelected(int color)
    {
        selected_color = color;
        content.setBackgroundResource(selected_color);
    }
}
