package com.xiaochj.greatfeatures;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaochj.greatfeatures.ipc.aidl.AIdlClient;
import com.xiaochj.greatfeatures.animations.pulltorefresh.PullToRefreshTest;
import com.xiaochj.greatfeatures.imageframe.glide.GlideTest;
import com.xiaochj.greatfeatures.imageframe.uil.UilTest;
import com.xiaochj.greatfeatures.ipc.messenger.MessengerClient;
import com.xiaochj.greatfeatures.network.okhttp.OkhttpTest;
import com.xiaochj.greatfeatures.network.retrofit.RetrofitTest;
import com.xiaochj.greatfeatures.network.volley.VolleyTest;

public class MainActivity extends ListActivity {

  private static final String TAG = "mainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate");
    setContentView(R.layout.activity_main);
    String[] mTitles = getResources().getStringArray(R.array.main_title);
    ArrayAdapter<String> arrayAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles);
    setListAdapter(arrayAdapter);
  }

  @Override protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    Intent intent = null;
    switch (position) {
      case 0:
        intent = new Intent(this, RetrofitTest.class);
        startActivity(intent);
        break;
      case 1:
        intent = new Intent(this, VolleyTest.class);
        startActivity(intent);
        break;
      case 2:
        intent = new Intent(this, OkhttpTest.class);
        startActivity(intent);
        break;
      case 3:
        intent = new Intent(this, GlideTest.class);
        startActivity(intent);
        break;
      case 4:
        intent = new Intent(this, UilTest.class);
        startActivity(intent);
        break;
      case 5:
        intent = new Intent(this, PullToRefreshTest.class);
        startActivity(intent);
        break;
      case 6:
        intent = new Intent(this, AIdlClient.class);
        startActivity(intent);
        break;
      case 7:
        intent = new Intent(this, MessengerClient.class);
        startActivity(intent);
    }
  }

  @Override protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart");
  }

  @Override protected void onRestart() {
    super.onRestart();
    Log.d(TAG, "onRestart");
  }

  @Override protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume");
  }

  @Override protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause");
  }

  @Override protected void onStop() {
    super.onStop();
    Log.d(TAG, "onStop");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy");
  }
}
