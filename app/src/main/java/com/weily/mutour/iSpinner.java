package com.weily.mutour;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class iSpinner extends LinearLayout
{
    private static final String TAG = "SpinnerDown";
    private String[] strings = new String[0];
    private Context context;
    private TextView text;
    private ListView listView;
    private LinearLayout head;

    public iSpinner(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ispinner, this);
        head = (LinearLayout) findViewById(R.id.i_spinner);
        text = (TextView) findViewById(R.id.head_text);
        listView = (ListView) findViewById(R.id.list);
    }

    public void setStrings(String[] strings)
    {
        Log.i(TAG, "setStrings: 加载数据");
        this.strings = strings;
        monitor();
    }

    private void monitor()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, R.layout.item_spinner, strings
        );
        listView.setAdapter(adapter);
        head.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listView.setVisibility(VISIBLE);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                text.setText(strings[position]);
                listView.setVisibility(GONE);
                Log.i(TAG, "onItemClick: " + strings[position]);
            }
        });
    }
}
