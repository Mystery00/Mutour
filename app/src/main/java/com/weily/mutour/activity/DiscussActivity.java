package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mystery0.tools.Logs.Logs;
import com.weily.mutour.ActivityMethod;
import com.weily.mutour.App;
import com.weily.mutour.R;
import com.weily.mutour.adapter.DiscussAdapter;
import com.weily.mutour.callback.DiscussListener;
import com.weily.mutour.class_class.Discuss;

import java.util.ArrayList;
import java.util.List;

public class DiscussActivity extends AppCompatActivity implements ActivityMethod, DiscussListener
{
    private static final String TAG = "DiscussActivity";
    private List<Discuss> list = new ArrayList<>();
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initialize();
        monitor();

        list.add(new Discuss(null, "title", "hint"));
        list.add(new Discuss("https://www.google.co.jp/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png", "title", "hint"));
        list.add(new Discuss(null, "title", "hint"));
        list.add(new Discuss(null, "title", "hint"));
        list.add(new Discuss(null, "title", "hint"));
    }

    @Override
    public void initialize()
    {
        setContentView(R.layout.activity_discuss);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(App.getContext()));
        DiscussAdapter adapter = new DiscussAdapter(list, this);
        recyclerView.setAdapter(adapter);

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
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Logs.i(TAG, "onClick: ");
            }
        });
    }

    @Override
    public void onItemClick(Discuss discuss, int position)
    {
        Logs.i(TAG, "onItemClick: " + discuss.getTitle());
    }
}
