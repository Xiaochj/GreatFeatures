package com.xiaochj.greatfeatures.network.okhttp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaochj.greatfeatures.MainApplication;
import com.xiaochj.greatfeatures.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Okhttp
 * Created by xiaochj on 17/1/10.
 */

public class OkhttpTest extends Activity {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient okHttpClient;
    private ScrollView mScrollView;
    private LinearLayout mLinearLayout;
    private TextView mGetTv,mPostTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        loadData();
    }

    private void initView(){
        mScrollView = new ScrollView(this);
        mScrollView.setLayoutParams(new FrameLayout.LayoutParams(-1,-2));
        mLinearLayout = new LinearLayout(this);
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mGetTv = new TextView(this);
        mPostTv = new TextView(this);
        mLinearLayout.addView(mGetTv);
        mLinearLayout.addView(mPostTv);
        mScrollView.addView(mLinearLayout);
        this.setContentView(mScrollView);
    }

    private void loadData(){
        okHttpClient = new OkHttpClient();
        getNetData();
        postNetData();
    }

    /**
     * get
     */
    private void getNetData(){
        Request request = new Request.Builder().url(MainApplication.GITHUB_URL).build();
        Call call = okHttpClient.newCall(request);
        //异步任务
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(OkhttpTest.this,getString(R.string.error),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mGetTv.setTextColor(Color.CYAN);
                        mGetTv.setText(str);
                    }
                });
            }
        });
    }

    /**
     * post
     */
    private void postNetData(){
        String json = jsonStr("Jesse", "Jake");
        RequestBody requestBody = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(MainApplication.ROUNDSAPP_URL).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(OkhttpTest.this,getString(R.string.error),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPostTv.setTextColor(Color.BLUE);
                        mPostTv.setText(str);
                    }
                });
            }
        });
    }

    String jsonStr(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

}
