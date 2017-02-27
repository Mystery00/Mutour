package com.weily.mutour.public_method;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.weily.mutour.App;
import com.weily.mutour.callback.onResponseListener;

import java.util.Map;

public class HttpUtil
{
    private int method;
    private String url;
    private Map<String, String> map;
    private onResponseListener onResponseListener;

    public HttpUtil setMethod(int method)
    {
        this.method = method;
        return this;
    }

    public HttpUtil setUrl(String url)
    {
        this.url = url;
        return this;
    }

    public HttpUtil setResponse(onResponseListener onResponseListener)
    {
        this.onResponseListener = onResponseListener;
        return this;
    }

    public HttpUtil setMap(Map<String, String> map)
    {
        this.map = map;
        return this;
    }

    public void open()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(App.getContext());
        StringRequest stringRequest = new StringRequest(method, this.url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        onResponseListener.response(1, response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        onResponseListener.response(0, volleyError.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
