package com.xiaochj.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by xiaochj on 2018/1/15.
 */

public class AidlService extends Service {

  //2、将接口的具体实现写在Stub中（转AIdlClient.java）
  IBinder iBinder = new IMyAidlInterface.Stub() {
    @Override public int add(int a, int b) throws RemoteException {
      return a + b;
    }
  };

  @Override public void onCreate() {
    super.onCreate();
  }

  @Nullable @Override public IBinder onBind(Intent intent) {
    //用ibinder对象传递给client
    return iBinder;
  }
}
