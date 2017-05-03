package com.bawei.texta1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.texta1.R;
import com.bawei.texta1.adapter.Adapters;
import com.bawei.texta1.bean.Info;
import com.bawei.texta1.util.CacheInterceptor;
import com.bawei.texta1.util.OkHttpClientManager;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p>
 * 类的用途：
 */
public class TryActivity extends Activity {
    private static final long cacheSize = 1024*1024*20;//缓存文件最大限制大小20M
    private static String cachedirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/caches";  //设置缓存文件路径
    private OkHttpClient client;

    private TextView text;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                Gson gson = new Gson();
                Info info = gson.fromJson(json, Info.class);
                ArrayList<Info.Data> data = info.data;
                Log.i("，，，",data.size()+"");
                File file = new File(cachedirectory);
                String s = GetFileSize(file);//计算文件大小
                clear.setText("clear"+s);//清楚按钮设置


            }
        }
    };
    private RecyclerView re5;
    private Button clear;
    private Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_main);
        Log.i("测试缓存地址",cachedirectory);
        initView();
       // serCatchData();
        getData();
        viewListner();

    }

    private void serCatchData() {
        //设置缓存的相关数据
        Cache cache = new Cache(new File(cachedirectory), cacheSize);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(8, TimeUnit.SECONDS);  //设置连接超时时间
        builder.writeTimeout(8, TimeUnit.SECONDS);//设置写入超时时间
        builder.readTimeout(8, TimeUnit.SECONDS);//设置读取数据超时时间
        builder.retryOnConnectionFailure(false);//设置不进行连接失败重试
        builder.addNetworkInterceptor(new CacheInterceptor());//添加拦截器
        builder.cache(cache);//这种缓存
        client = builder.build();
        String url="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
        DoGetAndCache(url,60000);//请求数据并且缓存
    }

    private void initView() {
        clear = (Button) findViewById(R.id.clear);
        location = (Button) findViewById(R.id.location);
        re5 = (RecyclerView) findViewById(R.id.re5);

    }
    void getData(){
        String url="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<Info>() {
            @Override
            public void onError(com.squareup.okhttp.Request request, Exception e) {

            }

            @Override
            public void onResponse(Info response) {
                //得到解析数据
                ArrayList<Info.Data> data = response.data;
                LinearLayoutManager manager=new LinearLayoutManager(TryActivity.this);
                re5.setLayoutManager(manager);
                Adapters adapters = new Adapters(data, TryActivity.this);
                re5.setAdapter(adapters);
            }
        });
    }
    void viewListner(){
        //点击清除缓存
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.getInstance().clearDiskCache();//清除磁盘缓存
                ImageLoader.getInstance().clearMemoryCache();//清除内存缓存
              /*  File file = new File(cachedirectory);
                if (file.exists()){
                    String s = GetFileSize(file);//计算文件大小
                    clear.setText("清"+s);
                    deleteFile(file);
                    Log.i("000","清楚缓存");
                }*/
            }
        });
        //点击定位
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
    private static void deleteFile(File file) {
        File Array[] = file.listFiles();
        for (File f : Array) {
            if (f.isFile()) {// 如果是文件
                    f.delete();
                    System.out.println("删除文件成功");
                    return;

            }
        }
        System.out.println("删除文件失败,该文件不存在");
    }

    public  Call DoGetAndCache(String url, int cache_maxAge_inseconds) {

        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().maxAge(cache_maxAge_inseconds, TimeUnit.SECONDS).build())
                .url(url).build();

        Call call = client.newCall(request);
        startrequest(call);
        return call;
    }
    private void startrequest(final Call call0) {

        try {

            call0.enqueue(new Callback() {

                @Override
                public void onFailure(Call arg0, IOException arg1) {
                    //请求失败

                }

                @Override
                public void onResponse(Call arg0, Response response) throws IOException {

                    //请求返回数据
                    final String string = response.body().string();
                    Message message = new Message();
                    message.what=0;
                    message.obj=string;
                    handler.sendMessage(message);

                }

            });

        } catch (Exception e) {



        }
    }
    public  String GetFileSize(File file){
        String size = "";
        if(file.exists() && file.isFile()){
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < 1024) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) +"GB";
            }
        }else if(file.exists() && file.isDirectory()){
            size = "";
        }else{
            size = "0BT";
        }
        Log.i("文件大小",size+"");
        return size;
    }


}
