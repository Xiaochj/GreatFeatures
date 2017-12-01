package com.xiaochj.greatfeatures.network.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xiaochj on 17/1/9.
 */

interface RetrofitImpl {

    @GET("users/{user}") rx.Observable<UserBean> getUserInfo(@Path("user") String user);

}
