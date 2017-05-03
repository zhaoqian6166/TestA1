package com.bawei.texta1.util;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p/>
 * 类的用途：
 */
public class NetUtil {
    Context context;
    private String json;
    //请求数据
    public String getData(String url){
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
       //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
       //new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                json = response.body().string();
               // return json;
            }
        });
        return json;
    }




}
