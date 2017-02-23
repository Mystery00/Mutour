package com.weily.mutour.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weily.mutour.R;
import com.weily.mutour.callback.NewLuvLetterListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class NewLuvLetterAdapter extends RecyclerView.Adapter<NewLuvLetterAdapter.ViewHolder>
{
    private List<Integer> colors = new ArrayList<>();
    private NewLuvLetterListener luvLetterListener;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView circleImageView;

        ViewHolder(View itemView)
        {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.circleImageView);
        }
    }

    public NewLuvLetterAdapter(NewLuvLetterListener luvLetterListener)
    {
        this.luvLetterListener = luvLetterListener;
        colors.add(R.drawable.ic_random);
        colors.add(R.color.color_red);
        colors.add(R.color.color_pink);
        colors.add(R.color.color_deep_purple);
        colors.add(R.color.color_purple);
        colors.add(R.color.color_indigo);
        colors.add(R.color.color_blue);
        colors.add(R.color.color_light_blue);
        colors.add(R.color.color_orange);
        colors.add(R.color.color_deep_orange);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_color_selector, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.circleImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (holder.getAdapterPosition() == 0)
                {
                    luvLetterListener.itemSelected(colors.get((int) (Math.random() * 9 + 1)));
                } else
                {
                    luvLetterListener.itemSelected(colors.get(holder.getAdapterPosition()));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.circleImageView.setImageResource(colors.get(position));
    }

    @Override
    public int getItemCount()
    {
        return colors.size();
    }
}
