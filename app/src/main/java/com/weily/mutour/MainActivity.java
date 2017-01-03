package com.weily.mutour;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private boolean isOpen = false;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private iSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initialize();
        monitor();
    }

    private void initialize()
    {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        spinner = (iSpinner) findViewById(R.id.spinner);

        String[] strings = new String[]{"1", "2", "3"};
        spinner.setStrings(strings);

        setSupportActionBar(toolbar);
    }

    private void monitor()
    {
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                } else
                {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isOpen)
                {
                    fab.setImageResource(R.drawable.ic_add);
                    isOpen = false;
                } else
                {
                    fab.setImageResource(R.drawable.ic_close);
                    isOpen = true;
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_search:
                Log.i(TAG, "onOptionsItemSelected: search");
                break;
            default:
                Log.i(TAG, "onOptionsItemSelected: default");
        }
        return super.onOptionsItemSelected(item);
    }
}
