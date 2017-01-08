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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private static final String TAG = "RecyclerViewAdapter";
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
            imageView = (ImageView) itemView.findViewById(R.id.item_head);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    public RecyclerViewAdapter(List<MainListShow> list)
    {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fullView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                Log.i(TAG, "onClick: " + position);
                MainListShow mainListShow = list.get(position);
                Log.i(TAG, "onClick: " + mainListShow.getText());
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
}
