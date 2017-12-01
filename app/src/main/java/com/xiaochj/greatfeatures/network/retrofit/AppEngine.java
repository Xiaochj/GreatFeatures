package com.xiaochj.greatfeatures.network.retrofit;

import android.content.Context;
import com.xiaochj.greatfeatures.MainApplication;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络框架
 * Created by xiaochj on 17/3/27.
 */
public final class AppEngine {

  private static AppEngine sAppEngine;
  private RetrofitImpl appService;
  private OkHttpClient appAuthClient;

  private AppEngine(Context context) {
    initRetrofit(context);
  }

  public static void init(Context context) {
    sAppEngine = new AppEngine(context);
  }

  public static synchronized AppEngine getInstance() {
    return sAppEngine;
  }

  private void initRetrofit(Context context) {
    appAuthClient = defaultOkHttpClient(context);
    appService = new Retrofit.Builder().baseUrl(MainApplication.GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(appAuthClient)
        .build()
        .create(RetrofitImpl.class);
  }

  private static OkHttpClient defaultOkHttpClient(Context context) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    //if (App.debug) {
    //  builder.addInterceptor(LoginInterceptor);//日志打印
    //}
    builder.connectTimeout(AppConstants.Http.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
    builder.readTimeout(AppConstants.Http.HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
    builder.writeTimeout(AppConstants.Http.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS);
    builder.addNetworkInterceptor(new AppInterceptor());
    builder.addInterceptor(new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        //String responseStr = response.body().string();
        //LogUtils.e("request:" + request.body().toString()+ "respone:" + responseStr);
        return response;
      }
    });
    final File baseDir = context.getCacheDir();
    if (baseDir != null) {
      final File cacheDir = new File(baseDir, AppConstants.RETROFIT_CACHE_FILE_NAME);
      builder.cache(new Cache(cacheDir, AppConstants.RETROFIT_CACHE_MAX_SIZE));
    }
    return builder.build();
  }

  public RetrofitImpl getAppService() {
    return appService;
  }
}
