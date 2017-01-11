package com.xiaochj.greatfeatures.network.volley;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.xiaochj.greatfeatures.MainApplication;
import com.xiaochj.greatfeatures.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Volley
 * Created by xiaochj on 17/1/10.
 */

public class VolleyTest extends Activity {

    private RequestQueue requestQueue;
    private LinearLayout mLinearLayout;
    private TextView mName,mId,mLocation;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        loadData();
    }

    private void initView(){
        mLinearLayout = new LinearLayout(this);
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mName = new TextView(this);
        mId = new TextView(this);
        mLocation = new TextView(this);
        mImageView = new ImageView(this);
        mLinearLayout.addView(mName);
        mLinearLayout.addView(mId);
        mLinearLayout.addView(mLocation);
        mLinearLayout.addView(mImageView);
        this.setContentView(mLinearLayout);
    }

    private void loadData(){
        requestQueue = Volley.newRequestQueue(this);
        getDataForTextView();
    }

    private void getDataForTextView() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(MainApplication.GITHUB_URL+"users/Xiaochj", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mName.setTextColor(Color.CYAN);
                mId.setTextColor(Color.CYAN);
                mLocation.setTextColor(Color.CYAN);
                try {
                    mName.setText(response.getString("name"));
                    mId.setText(response.getString("id"));
                    mLocation.setText(response.getString("location"));
                    getDataForImageView(response.getString("avatar_url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyTest.this,getString(R.string.error),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void getDataForImageView(String url){
        ImageLoader imageLoader = new ImageLoader(requestQueue, new LruBitmapCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mImageView,android.R.drawable.btn_default,android.R.drawable.stat_notify_error);
        imageLoader.get(url,imageListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        //页面退出,网络请求终止
        requestQueue.cancelAll(this);
    }
}
