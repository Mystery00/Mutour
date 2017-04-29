package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.weily.mutour.ActivityMethod;
import com.weily.mutour.R;
import com.weily.mutour.adapter.SchoolActivityAdapter;
import com.weily.mutour.callback.SchoolActivityListener;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity implements ActivityMethod, SchoolActivityListener
{
    private Toolbar toolbar;
    private List<com.weily.mutour.class_class.SchoolActivity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initialize();
        monitor();

        list.add(new com.weily.mutour.class_class.SchoolActivity("title1", "source1", null, null));
        list.add(new com.weily.mutour.class_class.SchoolActivity("title1", "source1", "http://pic.qiantucdn.com/58pic/18/74/53/56529dc91dd56_1024.jpg", null));
        list.add(new com.weily.mutour.class_class.SchoolActivity("title1", "source1", null, null));
        list.add(new com.weily.mutour.class_class.SchoolActivity("title1", "source1", "https://www.google.co.jp/logos/doodles/2017/tamas-18th-birthday-4812762818543616.4-s.png", null));
        list.add(new com.weily.mutour.class_class.SchoolActivity("title1", "source1", "https://www.google.co.jp/logos/doodles/2017/tamas-18th-birthday-4812762818543616.4-s.png", "123"));
        list.add(new com.weily.mutour.class_class.SchoolActivity("title1", "source1", "https://www.google.co.jp/logos/doodles/2017/tamas-18th-birthday-4812762818543616.4-s.png", "test"));
    }

    @Override
    public void initialize()
    {
        setContentView(R.layout.activity_school);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SchoolActivityAdapter adapter = new SchoolActivityAdapter(list, this);
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
    }

    @Override
    public void onItemClick(com.weily.mutour.class_class.SchoolActivity schoolActivity, int position)
    {

    }
}
