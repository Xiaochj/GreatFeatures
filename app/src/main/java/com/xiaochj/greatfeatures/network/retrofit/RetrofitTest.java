package com.xiaochj.greatfeatures.network.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaochj.greatfeatures.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit
 * Created by xiaochj on 17/1/6.
 */

public class RetrofitTest extends Activity{

    private static final String URL = "https://api.github.com/";
    private static final String USER = "Xiaochj";
    private Retrofit retrofit;
    private RetrofitImpl retrofitImpl;
    private Call<UserBean> userBeanCall;
    private LinearLayout mLinearLayout;
    private TextView mName,mId,mLocation;

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
        mLinearLayout.addView(mName);
        mLinearLayout.addView(mId);
        mLinearLayout.addView(mLocation);
        this.setContentView(mLinearLayout);
    }

    private void loadData() {
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitImpl = retrofit.create(RetrofitImpl.class);
        userBeanCall = retrofitImpl.getUserInfo(USER);
        userBeanCall.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean userBean = response.body();
                mName.setText(userBean.getName());
                mId.setText(userBean.getId()+"");
                mLocation.setText(userBean.getLocation());
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Toast.makeText(RetrofitTest.this,getString(R.string.error),Toast.LENGTH_LONG).show();
            }
        });
    }
}
