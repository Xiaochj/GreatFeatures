package com.xiaochj.greatfeatures.ipc.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by xiaochj on 2018/1/19.
 */

public class MessengerClient extends Activity {

  Messenger messenger;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LinearLayout ll = new LinearLayout(this);
    ll.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    Button button1 = new Button(this);
    button1.setText("绑定服务");
    Button button2 = new Button(this);
    button2.setText("messenger进程间通信");
    ll.addView(button1);
    ll.addView(button2);
    setContentView(ll);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent();
        intent.setPackage("com.xiaochj.aidlservice");
        intent.setAction("com.xiaochj.messenger.service");
        getApplicationContext().bindService(intent, new MyServiceConnection(), BIND_AUTO_CREATE);
        Toast.makeText(getApplicationContext(), "绑定成功", Toast.LENGTH_LONG).show();
      }
    });
    button2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Message msg = Message.obtain();
        msg.replyTo = replyMessenger;
        Bundle bundle = new Bundle();
        bundle.putString("data","client:去打golf去吗？");
        msg.setData(bundle);
        try {
          messenger.send(msg);
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    });
  }

  class MyServiceConnection implements ServiceConnection{

    @Override public void onServiceConnected(ComponentName name, IBinder service) {
        messenger = new Messenger(service);
    }

    @Override public void onServiceDisconnected(ComponentName name) {

    }
  }

  Messenger replyMessenger = new Messenger(new Handler(){
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      Toast.makeText(getApplicationContext(),msg.getData().getString("reply"),Toast.LENGTH_LONG).show();
    }
  });

}
