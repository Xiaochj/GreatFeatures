package com.xiaochj.greatfeatures.imageframe.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(300,150));
        //.with(。。。) 可以是context,activity,fragment
        //.placeholder(。。。) 默认的图片
        //.error(。。。) 加载失败显示的图片
        //.centerCrop() 图片裁剪 .fitCenter() 图片适中
        //.skipMemoryCache(。。。) 图片的缓存策略
        //.diskCacheStrategy(。。。) 硬盘的缓存策略
            // DiskCacheStrategy.NONE 什么都不缓存
            //DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
            //DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
            //DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
        Glide.with(this).load(url).placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error).skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).fitCenter().into(mImageView);
        mLinearLayout.addView(mImageView);
        this.setContentView(mLinearLayout);
    }
}
