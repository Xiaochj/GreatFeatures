package com.xiaochj.greatfeatures.imageframe.uil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * universal imageloader
 * Created by xiaochj on 17/1/20.
 */

public class UilTest extends Activity {

    static final String url = "http://news.cctvnews.cn/resource/live/english/cctv-news.png";
    LinearLayout mLinearLayout;
    ImageView mImageView;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayout = new LinearLayout(this);
        mLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1,-1));
        mImageView = new ImageView(this);
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(300,150));
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(imageLoaderConfiguration);
        imageLoader.displayImage(url, mImageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                mImageView.setBackgroundResource(android.R.drawable.stat_notify_error);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
        mLinearLayout.addView(mImageView);
        this.setContentView(mLinearLayout);
    }
}
