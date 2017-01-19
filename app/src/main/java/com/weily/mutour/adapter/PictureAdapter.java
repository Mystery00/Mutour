package com.weily.mutour.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.weily.mutour.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder>
{
    private static final String TAG = "PictureAdapter";
    private List<Integer> pathList;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        ViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }

    public PictureAdapter(List<Integer> pathList)
    {
        this.pathList = pathList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_image, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Log.i(TAG, "onLongClick: 长按" + viewHolder.getAdapterPosition());
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        //holder.imageView.setImageBitmap(BitmapFactory.decodeFile(pathList.get(position)));
        holder.imageView.setImageResource(pathList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return pathList.size();
    }
}
