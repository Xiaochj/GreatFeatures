package com.xiaochj.greatfeatures.templet.baseimpl;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xiaochj on 2018/2/24.
 */

public interface ActionbarImpl {

  //void setActionbarEnabled(boolean isEnable);

  boolean isActionbarEnabled();

  void setActionbarBackgroundColor(@ColorInt int color);

  void setActionbarBackgroundResource(@DrawableRes int resId);

  void setActionbarBackground(Drawable background);

  TextView setRightActionViewText(@StringRes int text, View.OnClickListener onClickListener);

  TextView setRightActionViewText(CharSequence text, View.OnClickListener onClickListener);

  TextView setRightActionViewText(@StringRes int text, @ColorInt int textColorResId,
      View.OnClickListener onClickListener);

  TextView setRightActionViewText(CharSequence text, @ColorInt int textColorResId,
      View.OnClickListener onClickListener);

  TextView SetRightActionViewDrawable(@DrawableRes int drawableResId,
      View.OnClickListener onClickListener);

  TextView setActionbarTitle(CharSequence title);

  TextView setActionbarTitle(@StringRes int text);

  TextView setActionbarTitleColor(@ColorInt int color);

  TextView setBackClickListener(View.OnClickListener listener);

  TextView setBackClickListener(@DrawableRes int drawableResId, View.OnClickListener listener);

  TextView setBackClickListener(Drawable drawable, View.OnClickListener listener);
}
