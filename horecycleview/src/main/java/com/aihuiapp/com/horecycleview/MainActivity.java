package com.aihuiapp.com.horecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CommonAdapter<String> mCommonAdapter;
    private ArrayList<String> mdatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false));    //设置为横向


        mCommonAdapter = new CommonAdapter<String>(this, R.layout.item, mdatas
        ) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv, s);
            }
        };
        rv.setAdapter(mCommonAdapter);


        getdatas();

    }

    private void getdatas() {
        for (int i = 0; i < 100; i++) {
            mdatas.add(i + "我我");
        }
        mCommonAdapter.notifyDataSetChanged();
    }


}
