package com.weily.mutour.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mystery0.ispinner.SpinnerItemClickListener;
import com.mystery0.ispinner.iSpinner;
import com.weily.ifloatmenu.MenuClick;
import com.weily.ifloatmenu.iFloatMenu;
import com.weily.mutour.adapter.DrawerMenuAdapter;
import com.weily.mutour.adapter.RecyclerViewAdapter;
import com.weily.mutour.class_class.MainListShow;
import com.weily.mutour.public_method.GetList;
import com.weily.mutour.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private iSpinner spinner;
    private iFloatMenu floatMenu;

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
        RecyclerView recyclerView_main = (RecyclerView) findViewById(R.id.recycler_view);
        View menu_start = findViewById(R.id.drawer_layout_start);
        View menu_end = findViewById(R.id.drawer_layout_end);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        spinner = (iSpinner) findViewById(R.id.spinner);
        floatMenu = (iFloatMenu) findViewById(R.id.i_float_menu);

        recyclerViewInit(menu_start);
        recyclerViewInit(menu_end);

        List<MainListShow> showList = GetList.getList(1);
        recyclerView_main.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(showList);
        recyclerView_main.setAdapter(viewAdapter);

        spinner.setStrings(getResources().getStringArray(R.array.books_classification));
        spinner.setSelected(0);
        spinner.setListBackground(Color.BLACK);

        floatMenu.setNumber(2);
        floatMenu.setIcon(R.drawable.ic_button_icon);
        floatMenu.setIcons(new int[]{R.drawable.ic_new_get, R.drawable.ic_new_post});

        setSupportActionBar(toolbar);
    }

    private void monitor()
    {
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toolbar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (spinner.isOpen())
                {
                    spinner.setLayoutVisiblity(View.GONE);
                }
            }
        });
        spinner.setOnItemClickListener(new SpinnerItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                switch (position)
                {
                    case 0:
                        Log.i(TAG, "onItemClick: 教材");
                        break;
                    case 1:
                        Log.i(TAG, "onItemClick: 文学");
                        break;
                }
            }
        });
        floatMenu.setMenuClickListener(new MenuClick()
        {
            @Override
            public void menuClick(int position)
            {
                switch (position)
                {
                    case 0://post
                        Log.i(TAG, "menuClick: 新书");
                        startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                        break;
                    case 1://get
                        Log.i(TAG, "menuClick: 求书");
                        break;
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
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Log.i(TAG, "onQueryTextSubmit: " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    private void recyclerViewInit(View parent)
    {
        RecyclerView recyclerView_menu = (RecyclerView) parent.findViewById(R.id.recycler_view_menu);
        List<MainListShow> menuList = GetList.getMenu(getApplicationContext());
        recyclerView_menu.setLayoutManager(new LinearLayoutManager(this));
        DrawerMenuAdapter drawerMenuAdapter = new DrawerMenuAdapter(menuList);
        recyclerView_menu.setAdapter(drawerMenuAdapter);
    }
}
