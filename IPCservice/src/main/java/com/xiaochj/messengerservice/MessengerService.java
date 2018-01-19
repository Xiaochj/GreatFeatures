package com.xiaochj.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

public class MessengerService extends Service {

  Messenger messenger = new Messenger(new Handler(){
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      Toast.makeText(getApplicationContext(),msg.getData().getString("data"),Toast.LENGTH_LONG).show();
      Message message = Message.obtain();
      Bundle bundle = new Bundle();
      bundle.putString("reply","server:gogo!");
      message.setData(bundle);
      try {
        msg.replyTo.send(message);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  });

  public MessengerService() {
  }

  @Override public IBinder onBind(Intent intent) {
    return messenger.getBinder();
  }
}
