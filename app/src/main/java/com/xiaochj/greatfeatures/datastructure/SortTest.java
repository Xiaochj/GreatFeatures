package com.xiaochj.greatfeatures.datastructure;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaochj.greatfeatures.R;
import com.xiaochj.greatfeatures.templet.base.BaseActivity;

/**
 * Created by xiaochj on 2018/3/28.
 */

public class SortTest extends BaseActivity {

  private int[] num = { 5, 4, 6, 2, 7, 1, 3 };

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
  }

  private void initView() {
    LinearLayout ll = new LinearLayout(this);
    ll.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    ll.setOrientation(LinearLayout.VERTICAL);

    TextView tv = new TextView(this);
    tv.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    tv.setText("冒泡排序：" + intToStr(bubbleSort(num)));

    TextView tv0 = new TextView(this);
    tv0.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    tv0.setText("选择排序：" + intToStr(selectSort(num)));

    TextView tv1 = new TextView(this);
    tv1.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    tv1.setText("插入排序：" + intToStr(insertSort(num)));

    ll.addView(tv);
    ll.addView(tv0);
    ll.addView(tv1);
    setContentView(ll);
    this.setActionbarBackgroundResource(R.color.colorPrimaryDark);
    this.setActionbarTitle("排序");
    this.setActionbarTitleColor(Color.WHITE);
  }

  /**
   * 冒泡排序
   * 时间复杂度O(N^2)，交换N^2次
   */
  public int[] bubbleSort(int[] num) {
    int len = num.length;
    int temp;
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        if (num[i] > num[j]) {
          temp = num[i];
          num[i] = num[j];
          num[j] = temp;
        }
      }
    }
    return num;
  }

  /**
   * 选择排序
   * 时间复杂度O(N^2)，交换N次，比冒泡快一些
   */
  public int[] selectSort(int[] num) {
    int min = num[0];
    int s = 0;
    int length = num.length;
    int tempS;
    while (s < length - 1) {
      tempS = s;
      for (int i = s + 1; i < length; i++) {
        if (num[i] < min) {
          min = num[i];
          tempS = i;
        }
      }
      int temp = num[s];
      num[s] = min;
      num[tempS] = temp;
      s++;
      min = num[s];
    }
    return num;
  }

  /**
   * 插入排序
   * 时间复杂度O(N^2)，复制（赋值）比交换要快，故效率高于选择排序
   */
  public int[] insertSort(int[] num) {
    for (int i = 1; i < num.length; i++) {
      int temp = num[i];
      int s = i;
      while (temp < num[s - 1] && s > 0) {
        num[s] = num[s - 1];
        s--;
      }
      num[s] = temp;
    }
    return num;
  }

  private String intToStr(int[] num) {
    StringBuffer sb = new StringBuffer(String.valueOf(num[0]));
    for (int i = 1; i < num.length; i++) {
      sb.append(",");
      sb.append(num[i]);
    }
    return sb.toString();
  }
}
