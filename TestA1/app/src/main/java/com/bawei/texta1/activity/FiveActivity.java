package com.bawei.texta1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.bawei.texta1.R;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;



/**
 * Created by 赵倩 on 2017/5/2.
 * <p/>
 * 类的用途：
 */
public class FiveActivity extends Activity {

    private RecyclerView re5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_main);
        initView();
    }

    private void initView() {
        Button clear= (Button) findViewById(R.id.clear);
        Button location= (Button) findViewById(R.id.location);
        re5 = (RecyclerView) findViewById(R.id.re5);


    }
    //网络请求获取数据
   void  getData(String path) {
       /*Cache cache = provideCache();
      // OkHttpClient okHttpClient = new OkHttpClient();

       OkHttpClient build = new OkHttpClient().newBuilder()
               .addNetworkInterceptor(new CacheInterceptor())
               .cache(cache)
               .connectTimeout(20, TimeUnit.SECONDS)
               .readTimeout(20, TimeUnit.SECONDS)
               .build();*/

       //build.newCall()
      /* OkHttpClientManager.getAsyn(path, new OkHttpClientManager.ResultCallback() {
           @Override
           public void onError(com.squareup.okhttp.Request request, Exception e) {

           }

           @Override
           public void onResponse(Object response) {
               //请求成功


           }
       });*/
       //开始请求数据 并设置参数
       //创建okHttpClient对象
       OkHttpClient mOkHttpClient = new OkHttpClient();
//创建一个Request
       final Request request = new Request.Builder()
               .url(path)
               .cacheControl(new CacheControl.Builder().maxAge(100000, TimeUnit.SECONDS).build())
               .build();

//new callOk
       Call call = mOkHttpClient.newCall(request);
//请求加入调度
       call.enqueue(new Callback() {
           @Override
           public void onFailure(Request request, IOException e) {

           }

           @Override
           public void onResponse(Response response) throws IOException {

           }
       });

   }
  /*  public static Call DoGetAndCache(String url,int cache_maxAge_inseconds) {

        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().maxAge(cache_maxAge_inseconds, TimeUnit.SECONDS).build())
                .url(url).build();

        Call call = client.newCall(request);
        startrequest(call);
        return call;
    }*/

  /*  public Cache provideCache() {
        return new Cache(FiveActivity.this.getCacheDir(), 10240*1024);
    }*/

}
