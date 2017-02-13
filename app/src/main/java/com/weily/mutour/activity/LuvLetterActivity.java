package com.weily.mutour.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.weily.mutour.R;
import com.weily.mutour.adapter.LuvLetterAdapter;
import com.weily.mutour.class_class.LuvLetter;

import java.util.ArrayList;
import java.util.List;

public class LuvLetterActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private List<LuvLetter> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //checkPermission();

        list.add(new LuvLetter("sender1", "text1", LuvLetter.BackgroundColor.BLUE));
        list.add(new LuvLetter("发送者2", "文本2", LuvLetter.BackgroundColor.DEEP_ORANGE));
        list.add(new LuvLetter("sender3", "text3", LuvLetter.BackgroundColor.DEEP_PURPLE));
        list.add(new LuvLetter("sender4", "text4", LuvLetter.BackgroundColor.LIGHT_BLUE));
        list.add(new LuvLetter("sender5", "text5", LuvLetter.BackgroundColor.PINK));
        list.add(new LuvLetter("sender6", "text6", LuvLetter.BackgroundColor.RED));
        list.add(new LuvLetter("sender7", "text7", LuvLetter.BackgroundColor.PURPLE));

        initialize();
        monitor();
    }

    private void initialize()
    {
        setContentView(R.layout.activity_luv_letter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.luv_letter_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LuvLetterAdapter drawerMenuAdapter = new LuvLetterAdapter(list);
        recyclerView.setAdapter(drawerMenuAdapter);

        setSupportActionBar(toolbar);
    }

    @SuppressWarnings("ConstantConditions")
    private void monitor()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }

}
