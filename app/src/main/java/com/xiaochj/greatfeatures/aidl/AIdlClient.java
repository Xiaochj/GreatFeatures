package com.xiaochj.greatfeatures.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.xiaochj.aidlservice.IMyAidlInterface;

/**
 * Created by xiaochj on 2018/1/15.
 */

public class AIdlClient extends Activity {

  IMyAidlInterface iMyAidlInterface;
  int sum = 0;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LinearLayout ll = new LinearLayout(this);
    ll.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    Button button1 = new Button(this);
    button1.setText("绑定服务");
    Button button2 = new Button(this);
    button2.setText("aidl进程间通信");
    ll.addView(button1);
    ll.addView(button2);
    setContentView(ll);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //3、绑定远程服务，传递远端进程的package以及定义的service的action
        Intent intent = new Intent();
        intent.setPackage("com.xiaochj.aidlservice");
        intent.setAction("com.xiaochj.aidl.service");
        getApplicationContext().bindService(intent, new MyServiceConnectino(), BIND_AUTO_CREATE);
        Toast.makeText(getApplicationContext(), "绑定成功", Toast.LENGTH_LONG).show();
      }
    });
    button2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "" + sum, Toast.LENGTH_LONG).show();
      }
    });
  }

  class MyServiceConnectino implements ServiceConnection {
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
      //4、通过asInterface的方式将binder对象转换成接口，再调用接口中的方法实现进程间的通信
      iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
      try {
        //调用接口方法
        sum = iMyAidlInterface.add(1, 2);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    @Override public void onServiceDisconnected(ComponentName name) {

    }
  }
}
