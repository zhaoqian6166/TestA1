package com.bawei.texta1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bawei.texta1.R;
import com.bawei.texta1.adapter.MyAdapter;
import com.bawei.texta1.adapter.MyAdapter2;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p>
 * 类的用途：
 */
public class ThirdActivity extends Activity {

    private RecyclerView re2;
    private ArrayList<String> listLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_main);
        initView();
    }

    private void initView() {
        RecyclerView re1= (RecyclerView) findViewById(R.id.re1);
        re2 = (RecyclerView) findViewById(R.id.re2);

        listLeft = new ArrayList<String>();
        listLeft.add("奶粉");
        listLeft.add("尿裤");
        listLeft.add("玩具");

        re1.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter2 myAdapter = new MyAdapter2(listLeft, this);//设置左边的适配器
        re1.setAdapter(myAdapter);
        //接口回调设置点击方法
        myAdapter.setOnClick(new MyAdapter2.Click() {
            @Override
            public void onClick(View v, int position) {
                Log.i("position",""+position);
                //右边条目的数据
                ArrayList<String> listRight = new ArrayList<String>();
                for (int i=0;i<30;i++){
                   // Log.i("内容",listLeft.get(position)+":"+i);
                    listRight.add(listLeft.get(position)+":"+i);
                }

                re2.setLayoutManager(new GridLayoutManager(ThirdActivity.this,3));
                MyAdapter myAdapter2 = new MyAdapter(listRight, ThirdActivity.this);
                re2.setAdapter(myAdapter2);
                Toast.makeText(ThirdActivity.this, listLeft.get(position), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
