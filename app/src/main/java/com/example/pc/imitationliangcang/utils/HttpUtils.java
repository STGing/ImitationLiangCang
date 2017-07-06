package com.example.pc.imitationliangcang.utils;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by PC on 2017/7/6.
 */

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    //使用单例模式
    private static HttpUtils mHttpUtils = null;
    private HttpUtils(){}
    public static HttpUtils getInstance() {
        synchronized (HttpUtils.class) {
            if (mHttpUtils == null) {
                mHttpUtils = new HttpUtils();
            }
        }
        return mHttpUtils;
    }


    //从网络获取数据
    public Observable<String> getNetData(final String url){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {

                OkGo.<String>get(url).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //连接成功的时候，返回结果
//                        Log.e(TAG, "onSuccess: 的结果===="+response.body().toString() );
                        e.onNext(response.body().toString());
                        e.onComplete();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e(TAG, "联网失败");
                    }
                });
            }
        });
    }
}
