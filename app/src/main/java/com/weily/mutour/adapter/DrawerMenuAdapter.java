package com.weily.mutour.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weily.mutour.class_class.MainListShow;
import com.weily.mutour.R;

import java.util.List;

public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuAdapter.ViewHolder>
{
    private static final String TAG = "DrawerMenuAdapter";
    private List<MainListShow> list;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fullView;
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView)
        {
            super(itemView);
            fullView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.item_menu_img);
            textView = (TextView) itemView.findViewById(R.id.item_menu_text);
        }
    }

    public DrawerMenuAdapter(List<MainListShow> list)
    {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_menu, parent, false);
        final DrawerMenuAdapter.ViewHolder holder = new DrawerMenuAdapter.ViewHolder(view);
        holder.fullView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                monitor(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        MainListShow mainListShow = list.get(position);
        holder.imageView.setImageResource(mainListShow.getImg());
        holder.textView.setText(mainListShow.getText());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    private void monitor(int position)
    {
        switch (position)
        {
            case 0:
                Log.i(TAG, "monitor: " + list.get(position).getText());
                break;
        }
    }
}
