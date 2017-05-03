package com.bawei.texta1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import com.bawei.texta1.R;
import com.bawei.texta1.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/1.
 * <p/>
 * 类的用途：
 */
public class SecondActivity extends Activity {
    Handler handler=new Handler();
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        initView();

    }

    private void initView() {
        RecyclerView re= (RecyclerView) findViewById(R.id.recycl);
        final SwipeRefreshLayout srl= (SwipeRefreshLayout) findViewById(R.id.srl);
        list = new ArrayList<String>();
        for (int i=0;i<30;i++){
            list.add("条目"+i);
        }
        re.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(list, SecondActivity.this);
        re.setAdapter(myAdapter);
      //  srl.setRefreshing(true);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新数据
                        Toast.makeText(SecondActivity.this, "刷新", Toast.LENGTH_SHORT).show();
                        srl.setRefreshing(false);
                    }
                },2000);

            }
        });

    }

}
