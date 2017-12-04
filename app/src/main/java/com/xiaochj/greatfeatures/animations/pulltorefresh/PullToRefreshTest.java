package com.xiaochj.greatfeatures.animations.pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaochj.greatfeatures.R;
import com.yalantis.phoenix.PullToRefreshView;
import java.util.ArrayList;

/**
 * phoenix pull-to-refresh 带动画的下拉刷新动画，可以修改源码的动画场景
 * https://github.com/Yalantis/Phoenix
 * Created by xiaochj on 2017/12/4.
 */

public class PullToRefreshTest extends Activity {

  private ArrayList<String> items = new ArrayList();
  PullToRefreshView pullToRefreshView;
  RecyclerView recyclerView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pull_to_refresh_layout);
    pullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    items.add("1");
    items.add("2");
    items.add("3");
    initLayout();
  }

  private void initLayout() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    final SmapleAdapter adapter = new SmapleAdapter();
    recyclerView.setAdapter(adapter);
    pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
      @Override public void onRefresh() {
        pullToRefreshView.postDelayed(new Runnable() {
          @Override public void run() {
            //延迟2s关闭下拉动画，并且刷新数据
            pullToRefreshView.setRefreshing(false);
            Toast.makeText(getApplicationContext(), "refresh ends", Toast.LENGTH_LONG).show();
            int size = items.size();
            items.add(String.valueOf(Integer.parseInt(items.get(size - 1)) + 1));
            adapter.notifyDataSetChanged();
          }
        }, 2000);
      }
    });
  }

  private class SmapleAdapter extends RecyclerView.Adapter<SmapleAdapter.ViewHolder> {

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = View.inflate(PullToRefreshTest.this, android.R.layout.simple_list_item_1, null);
      return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      holder.textView.setText(items.get(position));
    }

    @Override public int getItemCount() {
      return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;

      public ViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(android.R.id.text1);
      }
    }
  }
}












