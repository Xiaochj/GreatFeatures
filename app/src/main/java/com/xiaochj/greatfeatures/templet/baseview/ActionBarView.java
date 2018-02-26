package com.xiaochj.greatfeatures.templet.baseview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaochj.greatfeatures.R;

public class ActionBarView extends RelativeLayout {
  private TextView mLeftActionView;
  private TextView mTitleView;
  private TextView mRightActionView;
  private ActionBarView.ClickListener mClickListener;
  private View actionbar;

  public ActionBarView(Context context) {
    super(context);
    this.initView(context);
  }

  public ActionBarView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.initView(context);
  }

  public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.initView(context);
  }

  private void initView(Context context) {
    LayoutInflater.from(context).inflate(R.layout.base_actionbar, this);
    this.actionbar = this.findViewById(R.id.action_bar);
    this.mLeftActionView = (TextView) this.findViewById(R.id.left_action_view);
    this.mLeftActionView.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        if (ActionBarView.this.mClickListener != null) {
          ActionBarView.this.mClickListener.leftClick();
        }
      }
    });
    this.mTitleView = (TextView) this.findViewById(R.id.title);
    this.mRightActionView = (TextView) this.findViewById(R.id.right_action_view);
    this.mRightActionView.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        if (ActionBarView.this.mClickListener != null) {
          ActionBarView.this.mClickListener.rightClick();
        }
      }
    });
  }

  public void setClickListener(ActionBarView.ClickListener clickListener) {
    this.mClickListener = clickListener;
  }

  public TextView setTitle(CharSequence title) {
    if (title != null) {
      this.mTitleView.setText(title);
    }

    return this.mTitleView;
  }

  public TextView setTitle(@StringRes int resId) {
    this.mTitleView.setText(resId);
    return this.mTitleView;
  }

  public TextView setRightActionView(@StringRes int text, int textColorResId,
      OnClickListener listener) {
    return this.setRightActionView(this.getContext().getResources().getText(text), textColorResId,
        listener);
  }

  public TextView setRightActionView(CharSequence text, int textColorResId,
      OnClickListener listener) {
    this.mRightActionView.setText(text);
    if (textColorResId != 0) {
      this.mRightActionView.setTextColor(this.getResources().getColor(textColorResId));
    }

    if (listener != null) {
      this.mRightActionView.setOnClickListener(listener);
    }

    return this.mRightActionView;
  }

  public TextView setRightActionView(@DrawableRes int drawableResId, OnClickListener listener) {
    return this.setRightActionView(
        drawableResId != 0 ? ContextCompat.getDrawable(this.getContext(), drawableResId) : null,
        listener);
  }

  public TextView setRightActionView(Drawable drawable, OnClickListener listener) {
    this.mRightActionView.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null,
        (Drawable) null, (Drawable) null);
    if (listener != null) {
      this.mRightActionView.setOnClickListener(listener);
    }

    return this.mRightActionView;
  }

  public TextView setBackClickListener(OnClickListener listener) {
    if (listener != null) {
      this.mLeftActionView.setOnClickListener(listener);
    }

    return this.mLeftActionView;
  }

  public void setActionbarBackgroundColor(@ColorInt int color) {
    this.actionbar.setBackgroundColor(color);
  }

  public void setActionbarBackgroundResource(@DrawableRes int resid) {
    this.actionbar.setBackgroundResource(resid);
  }

  public void setActionbarBackground(Drawable background) {
    this.actionbar.setBackground(background);
  }

  public interface ClickListener {
    void leftClick();

    void rightClick();
  }
}
