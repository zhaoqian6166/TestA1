package com.bawei.texta1.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 赵倩 on 2017/5/2.
 * <p/>
 * 类的用途：
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Response response1 = response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //cache for 30 days
                .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                .build();
        return response1;
    }
}
