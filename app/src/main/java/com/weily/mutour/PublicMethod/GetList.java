package com.weily.mutour.PublicMethod;

import com.weily.mutour.Class.MainListShow;
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
            case 1:
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-1"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-2"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-3"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-4"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "1-5"));
                break;
            case 2:
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-1"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-2"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-3"));
                listShows.add(new MainListShow(R.drawable.ic_i_mu, "2-4"));
                listShows.add(new MainListShow(R.mipmap.ic_launcher, "2-5"));
                break;
        }
        return listShows;
    }
}
