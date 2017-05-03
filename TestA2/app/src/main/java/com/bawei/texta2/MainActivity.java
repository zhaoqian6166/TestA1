package com.bawei.texta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Request;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button clear;
    private Button location;
    private RecyclerView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
        viewListner();
    }

    private void initView() {
        clear = (Button) findViewById(R.id.clear);
        location = (Button) findViewById(R.id.location);
        re = (RecyclerView) findViewById(R.id.re);

    }
    void viewListner(){
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清楚缓存
                GlideCacheUtil instance = GlideCacheUtil.getInstance();
                instance.clearImageAllCache(MainActivity.this);
                Toast.makeText(MainActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                clear.setText("清除缓存");
            }
        });
        //定位
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //定位
                Intent intent=new Intent(MainActivity.this,BaiduActivity.class);
                startActivity(intent);

            }
        });

    }

    public void getData() {
        String url="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<Info>() {

            private ArrayList<Info.Data> data;

            @Override
            public void onError(Request request, Exception e) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Info response) {
                data = response.data;
                MyAdapter myAdapter = new MyAdapter(MainActivity.this, data);
                re.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                re.setAdapter(myAdapter);
                String catchSize = getCatchSize();
                clear.setText("清除缓存（"+catchSize+")");

            }
        });

    }
    //获得缓存大小
    String getCatchSize(){
        GlideCacheUtil instance = GlideCacheUtil.getInstance();
        String cacheSize = instance.getCacheSize(MainActivity.this);

        return cacheSize;

    }
}
