package com.weily.mutour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mystery0.tools.Logs.Logs;
import com.weily.mutour.R;

public class SplashActivity extends AppCompatActivity
{
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(getString(R.string.hitokoto_api),
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String s)
                            {
                                Intent intent = new Intent(SplashActivity.this, PageActivity.class);
                                intent.putExtra("text", s);
                                startActivity(intent);
                                finish();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError volleyError)
                            {
                                Logs.e(TAG, "onErrorResponse: " + volleyError.getMessage());
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                queue.add(stringRequest);
            }
        }).start();
    }
}
