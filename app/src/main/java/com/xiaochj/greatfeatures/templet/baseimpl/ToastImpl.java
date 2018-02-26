package com.xiaochj.greatfeatures.templet.baseimpl;

import android.support.annotation.StringRes;

/**
 * Created by xiaochj on 2018/2/24.
 */

public interface ToastImpl {

  void showToast(String message);

  void showToast(@StringRes int message);

  //void showToastOnCenter(String message);
}
