package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mystery0.tools.Logs.Logs;
import com.weily.mutour.ActivityMethod;
import com.weily.mutour.R;
import com.weily.mutour.adapter.MyBookAdapter;
import com.weily.mutour.callback.MyBookListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookActivity extends AppCompatActivity implements ActivityMethod, MyBookListener
{
    private static final String TAG = "MyBookActivity";
    private Toolbar toolbar;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initialize();
        monitor();
    }

    @Override
    public void initialize()
    {
        setContentView(R.layout.activity_my_book);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyBookAdapter myBookAdapter = new MyBookAdapter(list, this);
        recyclerView.setAdapter(myBookAdapter);

        setSupportActionBar(toolbar);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void monitor()
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
    public void onItemClick(int position)
    {
        Logs.i(TAG, "onItemClick: " + position);
    }
}
