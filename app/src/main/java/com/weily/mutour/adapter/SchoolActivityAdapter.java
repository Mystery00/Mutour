package com.weily.mutour.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weily.mutour.App;
import com.weily.mutour.R;
import com.weily.mutour.callback.SchoolActivityListener;
import com.weily.mutour.class_class.SchoolActivity;

import java.io.File;
import java.util.List;

public class SchoolActivityAdapter extends RecyclerView.Adapter<SchoolActivityAdapter.ViewHolder>
{
    private List<SchoolActivity> list;
    private SchoolActivityListener listener;

    public SchoolActivityAdapter(List<SchoolActivity> list, SchoolActivityListener listener)
    {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.item_school_activity, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(list.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        SchoolActivity schoolActivity = list.get(position);
        holder.title.setText(schoolActivity.getTitle());
        holder.source.setText(schoolActivity.getSource());
        File file = new File(schoolActivity.getImgPath());
        if (file.exists() && file.isFile())
        {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(schoolActivity.getImgPath()));
        }
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        TextView title;
        TextView source;
        ImageView imageView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            source = (TextView) itemView.findViewById(R.id.source);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
