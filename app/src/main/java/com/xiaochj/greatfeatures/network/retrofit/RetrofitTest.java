package com.xiaochj.greatfeatures.network.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaochj.greatfeatures.R;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Retrofit
 * Created by xiaochj on 17/1/6.
 */

public class RetrofitTest extends Activity {

  private static final String USER = "Xiaochj";
  private LinearLayout mLinearLayout;
  private TextView mName, mId, mLocation;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AppEngine.init(this);

    initView();
    loadData();
  }

  private void initView() {
    mLinearLayout = new LinearLayout(this);
    mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
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
    AppEngine.getInstance()
        .getAppService()
        .getUserInfo(USER)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<UserBean>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            Toast.makeText(RetrofitTest.this, getString(R.string.error), Toast.LENGTH_LONG).show();
          }

          @Override public void onNext(UserBean userBean) {
            mName.setText(userBean.getName());
            mId.setText(userBean.getId() + "");
            mLocation.setText(userBean.getLocation());
          }
        });
  }
}
