package com.weily.mutour.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weily.mutour.Class.MainListShow;
import com.weily.mutour.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter
{
    private List<MainListShow> list;
    private Context context;

    private class ViewHolder
    {
        ImageView imageView;
        TextView textView;
    }

    public ListViewAdapter(List<MainListShow> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MainListShow show = list.get(position);
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_head);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(show.getImg());
        viewHolder.textView.setText(show.getText());
        return convertView;
    }
}
