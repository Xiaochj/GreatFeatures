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

public class SelectSort extends BaseActivity {

  private int[] num = {5,4,6,2,7,1,3};

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
  }

  private void initView() {
    LinearLayout ll = new LinearLayout(this);
    ll.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    TextView tv = new TextView(this);
    tv.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    tv.setText(intToStr(selectSort(num)));
    ll.addView(tv);
    setContentView(ll);
    this.setActionbarBackgroundResource(R.color.colorPrimaryDark);
    this.setActionbarTitle("选择排序");
    this.setActionbarTitleColor(Color.WHITE);
  }

  /**
   * 选择排序
   * 时间复杂度O(N^2)，交换N次
   * @param num
   * @return
   */
  public int[] selectSort(int[] num){
    int min = num[0];
    int s = 0;
    int length = num.length;
    int tempS;
    while(s < length-1){
      tempS = s;
      for(int i = s+1;i< length;i++){
        if(num[i] < min){
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

  private String intToStr(int[] num){
    StringBuffer sb = new StringBuffer(String.valueOf(num[0]));
    for(int i = 1;i<num.length;i++){
      sb.append(",");
      sb.append(num[i]);
    }
    return sb.toString();
  }
}
