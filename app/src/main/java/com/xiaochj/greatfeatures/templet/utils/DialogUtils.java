package com.xiaochj.greatfeatures.templet.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * 弹窗工具类
 *
 *
 */
public class DialogUtils {

  /**
   * 局部加载对话框
   * 一个菊花
   *
   * @param activity
   * @return
   */
  public static Dialog showLocalProgress(Activity activity) {
    try {
      final AlertDialog dialog = new AlertDialog.Builder(activity, android.R.style.Widget_ProgressBar).create();
      dialog.setCanceledOnTouchOutside(false);
      if (activity != null && !activity.isFinishing()) {
        dialog.show();
      }
      return dialog;
    } catch (Exception ignored) {
      return null;
    }
  }

  /**
   * 局部加载对话框
   * 一个菊花
   *
   * @param activity
   * @return
   */
  public static Dialog showLocalProgress(Activity activity, int x, int y) {
    try {
      final AlertDialog dialog = new AlertDialog.Builder(activity, android.R.style.Widget_ProgressBar).create();
      dialog.setCanceledOnTouchOutside(false);
      if (activity != null && !activity.isFinishing()) {
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = x;
        lp.y = y;
        window.setAttributes(lp);
      }
      return dialog;
    } catch (Exception ignored) {
      return null;
    }
  }

  public static Dialog showLocalProgress(Context context) {
    try {
      if (context instanceof Activity) {
        return showLocalProgress((Activity) context);
      } else {
        final AlertDialog dialog = new AlertDialog.Builder(context, android.R.style.Widget_ProgressBar).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
      }
    } catch (Exception ignored) {
      return null;
    }
  }

  /**
   * 显示process弹窗, 可用于loading或其他process(cancelable = false)
   *
   * @since V4.1
   */
  public static Dialog showProcessDialog(Context context, String msg) {
    try {
      return ProgressDialog.show(context, "", TextUtils.isEmpty(msg) ? "处理中…" : msg, true, false);
    } catch (Exception ignored) {
      return null;
    }
  }


  public static void show(AlertDialog dialog) {

    if (dialog == null) {
      return;
    }
    try {
      dialog.setCanceledOnTouchOutside(false);
      dialog.show();
    } catch (Exception e) {
      Log.e("error", "exception: " + e.getLocalizedMessage());
    }

  }

  /**
   * @since V4.1
   */
  public static void dismissDialog(Dialog dialog) {
    try {
      if (dialog != null && dialog.isShowing()) {
        dialog.dismiss();
      }
    } catch (Exception ignored) {
    }
  }
}