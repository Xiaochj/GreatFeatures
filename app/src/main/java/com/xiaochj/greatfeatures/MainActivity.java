package com.xiaochj.greatfeatures;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaochj.greatfeatures.network.retrofit.RetrofitTest;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] mTitles = getResources().getStringArray(R.array.main_title);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mTitles);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = null;
        switch (position){
            case 0:
                intent = new Intent(this, RetrofitTest.class);
                startActivity(intent);
               break;
        }
    }
}
