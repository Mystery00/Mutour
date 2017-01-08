package com.weily.mutour.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mystery0.ispinner.SpinnerItemClickListener;
import com.mystery0.ispinner.iSpinner;
import com.weily.ifloatmenu.MenuClick;
import com.weily.ifloatmenu.iFloatMenu;
import com.weily.mutour.Adapter.ListViewAdapter;
import com.weily.mutour.Class.MainListShow;
import com.weily.mutour.PublicMethod.GetList;
import com.weily.mutour.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private iSpinner spinner;
    private iFloatMenu floatMenu;
    private ListView listView;
    private ListViewAdapter adapter;
    private List<MainListShow> showList;
    private DrawerMenu drawerMenu;

    private class DrawerMenu
    {
        TextView img_head = (TextView) findViewById(R.id.img_head);
        TextView imu_blog = (TextView) findViewById(R.id.imu_blog);
        TextView luv_letter = (TextView) findViewById(R.id.luv_letter);
        TextView activity = (TextView) findViewById(R.id.activity);
        TextView book = (TextView) findViewById(R.id.book);
        TextView borrow = (TextView) findViewById(R.id.borrow);
        TextView recommend = (TextView) findViewById(R.id.recommend);
        TextView store = (TextView) findViewById(R.id.store);
        TextView discuss = (TextView) findViewById(R.id.discuss);
        TextView setting = (TextView) findViewById(R.id.setting);
    }

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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        spinner = (iSpinner) findViewById(R.id.spinner);
        floatMenu = (iFloatMenu) findViewById(R.id.i_float_menu);
        listView = (ListView) findViewById(R.id.listView);
        drawerMenu = new DrawerMenu();

        showList = GetList.getList(1);
        adapter = new ListViewAdapter(showList, getApplicationContext());
        listView.setAdapter(adapter);

        String[] strings = getResources().getStringArray(R.array.books_classification);
        spinner.setStrings(strings);
        spinner.setSelected(0);

        floatMenu.setOpenIcon(R.drawable.ic_open_icon);
        floatMenu.setCloseIcon(R.drawable.ic_close_icon);
        floatMenu.setNumber(3);

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
                } else if (drawer.isDrawerOpen(GravityCompat.END))
                {
                    drawer.closeDrawer(GravityCompat.END);
                } else
                {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                spinner.setLayoutVisiblity(View.GONE);
            }
        });
        spinner.setOnItemClickListener(new SpinnerItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                Log.i(TAG, "onItemClick: Spinner " + position);
            }
        });
        floatMenu.setMenuClickListener(new MenuClick()
        {
            @Override
            public void menuClick(int position)
            {
                Log.i(TAG, "menuClick: FloatMenu " + position);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.i(TAG, "onItemClick: ListView " + position);
            }
        });
        drawerMenu.img_head.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "onClick: img_head");
            }
        });
        drawerMenu.setting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "onClick: setting");
            }
        });
        drawerMenu.activity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "onClick: activity");
                showList = GetList.getList(2);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END))
        {
            drawer.closeDrawer(GravityCompat.END);
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
