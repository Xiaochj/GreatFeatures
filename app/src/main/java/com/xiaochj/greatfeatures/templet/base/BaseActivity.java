package com.xiaochj.greatfeatures.templet.base;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaochj.greatfeatures.templet.baseimpl.ActionbarImpl;
import com.xiaochj.greatfeatures.templet.baseimpl.DialogImpl;
import com.xiaochj.greatfeatures.templet.baseimpl.ToastImpl;
import com.xiaochj.greatfeatures.templet.baseview.ActionBarView;
import com.xiaochj.greatfeatures.templet.utils.DialogUtils;

/**
 * Created by xiaochj on 2018/2/24.
 */

public class BaseActivity extends FragmentActivity implements ActionbarImpl, DialogImpl, ToastImpl {

  ActionBarView mActionBarView;
  Dialog mProcessDialog = null;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override public void setContentView(int layoutResID) {
    if (this.isActionbarEnabled()) {
      LinearLayout parent = new LinearLayout(this);
      parent.setOrientation(LinearLayout.VERTICAL);
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
      parent.setLayoutParams(layoutParams);
      addActionBarView(parent);
      try {
        super.setContentView(parent);
      } catch (Exception var9) {
        try {
          super.setContentView(layoutResID);
        } catch (Exception var8) {
          this.finish();
        }
      }
    } else {
      try {
        super.setContentView(layoutResID);
      } catch (Exception var7) {
        try {
          super.setContentView(layoutResID);
        } catch (Exception var6) {
          this.finish();
        }
      }
    }
  }

  @Override public void setContentView(View view) {
    if (this.isActionbarEnabled()) {
      LinearLayout parent = new LinearLayout(this);
      parent.setOrientation(LinearLayout.VERTICAL);
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
      parent.setLayoutParams(layoutParams);
      addActionBarView(parent);
      super.setContentView(parent);
    } else {
      super.setContentView(view);
    }
  }

  @Override public void setContentView(View view, ViewGroup.LayoutParams params) {
    if (this.isActionbarEnabled()) {
      LinearLayout parent = new LinearLayout(this);
      parent.setLayoutParams(params);
      addActionBarView(parent);
      super.setContentView(parent);
    } else {
      super.setContentView(view, params);
    }
  }

  private void addActionBarView(LinearLayout parent) {
    this.mActionBarView = new ActionBarView(this);
    this.mActionBarView.setClickListener(new BaseActivity.ActionBarClickListener());
    parent.addView(this.mActionBarView);
  }

  @Override public boolean showDialog() {
    if(this.isFinishing()) {
      return false;
    } else {
      DialogUtils.dismissDialog(this.mProcessDialog);
      this.mProcessDialog = DialogUtils.showLocalProgress(this);
      return true;
    }
  }

  @Override public boolean dismissDialog() {
    if(this.isFinishing()) {
      return false;
    } else {
      DialogUtils.dismissDialog(this.mProcessDialog);
      this.mProcessDialog = null;
      return true;
    }
  }

  @Override public Dialog getDialog() {
    return mProcessDialog;
  }

  @Override public void showToast(String message) {
    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
  }

  @Override public void showToast(int message) {
    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
  }

  //@Override public void showToastOnCenter(String message) {
  //
  //}

  @Override public boolean isActionbarEnabled() {
    return false;
  }

  @Override public void setActionbarBackgroundColor(int color) {
    if(mActionBarView!= null)
      mActionBarView.setActionbarBackgroundColor(color);
  }

  @Override public void setActionbarBackgroundResource(int resId) {
    if(mActionBarView != null)
      mActionBarView.setActionbarBackgroundResource(resId);
  }

  @Override public void setActionbarBackground(Drawable background) {
    if(mActionBarView != null)
      mActionBarView.setActionbarBackground(background);
  }

  @Override public TextView setRightActionViewText(int text, View.OnClickListener onClickListener) {
    return mActionBarView != null ? mActionBarView.setRightActionView(text,0,onClickListener) : null;
  }

  @Override
  public TextView setRightActionViewText(CharSequence text, View.OnClickListener onClickListener) {
    return mActionBarView != null ? mActionBarView.setRightActionView(text,0,onClickListener) : null;
  }

  @Override public TextView setRightActionViewText(int text, int textColorResId,
      View.OnClickListener onClickListener) {
    return mActionBarView != null ? mActionBarView.setRightActionView(text,textColorResId,onClickListener) : null;
  }

  @Override public TextView setRightActionViewText(CharSequence text, int textColorResId,
      View.OnClickListener onClickListener) {
    return mActionBarView != null ? mActionBarView.setRightActionView(text,0,onClickListener) : null;
  }

  @Override public TextView SetRightActionViewDrawable(int drawableResId,
      View.OnClickListener onClickListener) {
    return this.mActionBarView != null?this.mActionBarView.setRightActionView(drawableResId, onClickListener):null;
  }

  @Override public TextView setActionbarTitle(CharSequence title) {
    return this.mActionBarView != null?this.mActionBarView.setTitle(title):null;
  }

  @Override public TextView setActionbarTitle(@StringRes int textId) {
    return this.mActionBarView != null?this.mActionBarView.setTitle(textId):null;
  }

  @Override public TextView setBackClickListener(View.OnClickListener listener) {
    return this.mActionBarView != null?this.mActionBarView.setBackClickListener(listener):null;
  }

  @Override public TextView setBackClickListener(int drawableResId, View.OnClickListener listener) {
    TextView textView = null;
    if(this.mActionBarView != null) {
      textView = this.mActionBarView.setBackClickListener(listener);
    }

    if(textView != null) {
      textView.setCompoundDrawablesWithIntrinsicBounds(drawableResId, 0, 0, 0);
    }
    return textView;
  }

  @Override public TextView setBackClickListener(Drawable drawable, View.OnClickListener listener) {
    TextView textView = null;
    if(this.mActionBarView != null) {
      textView = this.mActionBarView.setBackClickListener(listener);
    }

    if(textView != null) {
      textView.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable)null, (Drawable)null, (Drawable)null);
    }
    return textView;
  }

  private class ActionBarClickListener implements ActionBarView.ClickListener {
    private ActionBarClickListener() {
    }

    @Override public void leftClick() {
      BaseActivity.this.onBackPressed();
    }

    @Override public void rightClick() {
    }
  }
}
