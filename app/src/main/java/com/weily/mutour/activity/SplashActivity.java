package com.weily.mutour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.weily.mutour.R;

public class SplashActivity extends AppCompatActivity
{
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(getString(R.string.hitokoto_api),
                    s -> {
                        Intent intent = new Intent(SplashActivity.this, PageActivity.class);
                        intent.putExtra("text", s);
                        startActivity(intent);
                        finish();
                    },
                    volleyError -> {
                        Log.e(TAG, "onErrorResponse: " + volleyError.getMessage(), volleyError);
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
            queue.add(stringRequest);
        }).start();
    }
}
