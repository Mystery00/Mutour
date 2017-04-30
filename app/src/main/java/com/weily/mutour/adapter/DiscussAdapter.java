package com.weily.mutour.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.mystery0.tools.ImageLoader.ImageCache;
import com.weily.mutour.App;
import com.weily.mutour.R;
import com.weily.mutour.callback.DiscussListener;
import com.weily.mutour.class_class.Discuss;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiscussAdapter extends RecyclerView.Adapter<DiscussAdapter.ViewHolder>
{
    private List<Discuss> list;
    private DiscussListener listener;

    public DiscussAdapter(List<Discuss> list, DiscussListener listener)
    {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.item_recycler_view_discuss, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.fullView.setOnClickListener(new View.OnClickListener()
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
        Discuss discuss = list.get(position);
        if (discuss.getImgPath() != null && !discuss.getImgPath().equals(""))
        {
            RequestQueue requestQueue = Volley.newRequestQueue(App.getContext());
            ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageCache(App.getContext()));
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.circleImageView, R.mipmap.image_default, R.mipmap.image_faild);
            imageLoader.get(discuss.getImgPath(), listener);
        } else
        {
            holder.circleImageView.setImageResource(R.mipmap.ic_launcher);
        }
        holder.textView_title.setText(discuss.getTitle());
        holder.textView_hint.setText(discuss.getHint());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fullView;
        CircleImageView circleImageView;
        TextView textView_title;
        TextView textView_hint;
        View line;

        ViewHolder(View itemView)
        {
            super(itemView);
            fullView = itemView;
            circleImageView = (CircleImageView) itemView.findViewById(R.id.img_head);
            textView_title = (TextView) itemView.findViewById(R.id.title);
            textView_hint = (TextView) itemView.findViewById(R.id.hint);
            line = itemView.findViewById(R.id.line);
        }
    }
}
