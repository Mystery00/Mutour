package com.weily.mutour.public_method;

import android.content.Context;

import com.weily.mutour.class_class.MainListShow;
import com.weily.mutour.R;

import java.util.ArrayList;
import java.util.List;

public class GetList
{
    public static List<MainListShow> getList(int type)
    {
        List<MainListShow> listShows = new ArrayList<>();
        switch (type)
        {
            case 2:
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-1"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-2"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-3"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-4"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-5"));
                break;
            case 1:
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-1"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-2"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-3"));
                listShows.add(new MainListShow(R.drawable.ic_i_mu, "2-4"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-5"));
                break;
        }
        return listShows;
    }

    public static List<MainListShow> getMenu(Context context)
    {
        List<MainListShow> listShows = new ArrayList<>();
        listShows.add(new MainListShow(R.drawable.ic_i_mu, context.getString(R.string.nav_i_mu_blog)));
        listShows.add(new MainListShow(R.drawable.ic_luv_letter, context.getString(R.string.nav_luv_letter)));
        listShows.add(new MainListShow(R.drawable.ic_calendar, context.getString(R.string.nav_school_activity)));
        listShows.add(new MainListShow(R.drawable.ic_my_books, context.getString(R.string.nav_my_books)));
        listShows.add(new MainListShow(R.drawable.ic_borrowed, context.getString(R.string.nav_borrowed_books)));
        listShows.add(new MainListShow(R.drawable.ic_recommend, context.getString(R.string.nav_recommend_books)));
        listShows.add(new MainListShow(R.drawable.ic_store, context.getString(R.string.nav_store)));
        listShows.add(new MainListShow(R.drawable.ic_discuss, context.getString(R.string.nav_discuss)));
        listShows.add(new MainListShow(R.drawable.ic_setting, context.getString(R.string.nav_setting)));
        return listShows;
    }
}
