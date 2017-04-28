package com.weily.mutour.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weily.mutour.App;
import com.weily.mutour.R;
import com.weily.mutour.callback.MyBookListener;

import java.util.List;

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.ViewHolder>
{
    private List<String> list;
    private MyBookListener listener;

    public MyBookAdapter(List<String> list, MyBookListener listener)
    {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.item_recycler_view_book, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        ViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_my_book_text);
        }
    }
}
