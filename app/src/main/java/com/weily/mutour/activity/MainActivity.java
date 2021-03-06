package com.weily.mutour.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mystery0.tools.FloatMenu.MenuClick;
import com.mystery0.tools.FloatMenu.iFloatMenu;
import com.mystery0.tools.Logs.Logs;
import com.mystery0.tools.iSpinner.SpinnerItemClickListener;
import com.mystery0.tools.iSpinner.iSpinner;
import com.weily.mutour.ActivityMethod;
import com.weily.mutour.adapter.DrawerMenuAdapter;
import com.weily.mutour.adapter.MainRecyclerViewAdapter;
import com.weily.mutour.callback.DrawerItemListener;
import com.weily.mutour.callback.MainRecyclerItemListener;
import com.weily.mutour.class_class.MainListShow;
import com.weily.mutour.public_method.GetList;
import com.weily.mutour.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ActivityMethod, DrawerItemListener, MainRecyclerItemListener, View.OnClickListener
{
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private iSpinner spinner;
    private iFloatMenu floatMenu;
    private MainRecyclerViewAdapter viewAdapter;
    private List<MainListShow> showList;
    private TextView head_start;
    private TextView head_end;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initialize();
        monitor();
    }

    public void initialize()
    {
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView_main = (RecyclerView) findViewById(R.id.recycler_view);
        View menu_start = findViewById(R.id.drawer_layout_start);
        View menu_end = findViewById(R.id.drawer_layout_end);
        head_start = (TextView) menu_start.findViewById(R.id.img_head);
        head_end = (TextView) menu_end.findViewById(R.id.img_head);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        spinner = (iSpinner) findViewById(R.id.spinner);
        floatMenu = (iFloatMenu) findViewById(R.id.i_float_menu);

        recyclerViewInit(menu_start);
        recyclerViewInit(menu_end);

        showList = GetList.getList(1);
        recyclerView_main.setLayoutManager(new LinearLayoutManager(this));
        viewAdapter = new MainRecyclerViewAdapter(showList, this);
        recyclerView_main.setAdapter(viewAdapter);

        spinner.setStrings(getResources().getStringArray(R.array.books_classification));
        spinner.setSelected(1);

        floatMenu.setNumber(2);
        floatMenu.setIcon(R.drawable.ic_button_icon);
        floatMenu.setIcons(new int[]{R.drawable.ic_new_get, R.drawable.ic_new_post});

        setSupportActionBar(toolbar);
    }

    public void monitor()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
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
                        showList.clear();
                        showList.addAll(GetList.getList(2));
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        showList.clear();
                        showList.addAll(GetList.getList(1));
                        viewAdapter.notifyDataSetChanged();
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
                        Logs.i(TAG, "menuClick: 新书");
                        startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                        break;
                    case 1://get
                        Logs.i(TAG, "menuClick: 求书");
                        break;
                }
            }
        });
        head_start.setOnClickListener(this);
        head_end.setOnClickListener(this);
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
                Logs.i(TAG, "onQueryTextSubmit: " + query);
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
        List<MainListShow> menuList = GetList.getMenu();
        recyclerView_menu.setLayoutManager(new LinearLayoutManager(this));
        DrawerMenuAdapter drawerMenuAdapter = new DrawerMenuAdapter(menuList, this);
        recyclerView_menu.setAdapter(drawerMenuAdapter);
    }

    @Override
    public void onItemClick(int position, String text)
    {
        Logs.i(TAG, "onItemClick: position: " + position);
        Logs.i(TAG, "onItemClick: text: " + text);
        switch (position)
        {
            case 0:
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "https://mystery00.github.io");
                startActivity(intent);
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, LuvLetterActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, SchoolActivity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, MyBookActivity.class));
                break;
            case 7:
                startActivity(new Intent(MainActivity.this, DiscussActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(MainListShow mainListShow, int position)
    {
        Logs.i(TAG, "onItemClick: " + mainListShow.getText());
        Logs.i(TAG, "onItemClick: " + position);
    }

    @Override
    public void onClick(View v)
    {
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
    }
}
