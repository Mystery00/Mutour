package com.weily.mutour.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weily.mutour.R;
import com.weily.mutour.callback.LuvLetterListener;
import com.weily.mutour.class_class.LuvLetter;

import java.util.List;

public class LuvLetterAdapter extends RecyclerView.Adapter<LuvLetterAdapter.ViewHolder>
{
    private List<LuvLetter> list;
    private LuvLetterListener letterListener;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fullView;
        TextView sender;
        TextView text;

        ViewHolder(View itemView)
        {
            super(itemView);
            fullView = itemView;
            sender = (TextView) itemView.findViewById(R.id.sender);
            text = (TextView) itemView.findViewById(R.id.card_text);
        }
    }

    public LuvLetterAdapter(List<LuvLetter> list, LuvLetterListener letterListener)
    {
        this.list = list;
        this.letterListener = letterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_luv_letter, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fullView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                letterListener.itemClick(list.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        LuvLetter luvLetter = list.get(position);
        holder.sender.setText(luvLetter.getSender());
        holder.text.setText(luvLetter.getText());
        holder.text.setBackgroundResource(luvLetter.getBackground_color());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
