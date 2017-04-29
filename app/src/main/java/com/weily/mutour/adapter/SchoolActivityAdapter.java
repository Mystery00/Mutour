package com.weily.mutour.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.mystery0.tools.ImageLoader.ImageCache;
import com.weily.mutour.App;
import com.weily.mutour.R;
import com.weily.mutour.callback.SchoolActivityListener;
import com.weily.mutour.class_class.SchoolActivity;

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
        if (position == list.size() - 1)
        {
            holder.line.setVisibility(View.GONE);
        }
        SchoolActivity schoolActivity = list.get(position);
        holder.title.setText(schoolActivity.getTitle());
        holder.source.setText(schoolActivity.getSource());
        if (schoolActivity.getImgPath() != null && !schoolActivity.getImgPath().equals(""))
        {
            String fileName = schoolActivity.getImgCacheName();
            RequestQueue requestQueue = Volley.newRequestQueue(App.getContext());
            ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageCache(App.getContext(), fileName == null || fileName.equals("") ? null : fileName));
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.imageView, R.mipmap.image_default, R.mipmap.image_faild);
            imageLoader.get(schoolActivity.getImgPath(), listener, 480, 300);
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
        View line;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            source = (TextView) itemView.findViewById(R.id.source);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            line = itemView.findViewById(R.id.line);
        }
    }
}
