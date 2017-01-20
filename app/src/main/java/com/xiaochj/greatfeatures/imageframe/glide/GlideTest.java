package com.xiaochj.greatfeatures.imageframe.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

/**
 * Glide
 * Created by xiaochj on 17/1/20.
 */

public class GlideTest extends Activity {

    static final String url = "http://news.cctvnews.cn/resource/live/english/cctv-news.png";
    LinearLayout mLinearLayout;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayout = new LinearLayout(this);
        mLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1,-1));
        mImageView = new ImageView(this);
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(-2,-2));
        Glide.with(this).load(url).thumbnail(0.1f).into(mImageView);
        mLinearLayout.addView(mImageView);
        this.setContentView(mLinearLayout);
    }
}
