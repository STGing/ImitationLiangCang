package com.example.pc.imitationliangcang.utils;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * 传递数据
 */

public class RxBus {

    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    private static class Holder {
        private static RxBus instance = new RxBus();
    }

    public static RxBus getInstance() {
        return Holder.instance;
    }

    /**
     * 发送消息:RxBus.getInstance().post("111");
     * @param obj
     */
    public void post(@NonNull Object obj) {
        mBus.onNext(obj);
    }

    /**
     * 接收消息:
     *          Flowable<String> f = RxBus.getInstance().register(String.class);
                f.subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Exception {
                        //处理消息
                        toast(integer);
                    }
                });
     * @param clz
     * @param <T>
     * @return
     */
    public <T> Flowable<T> register(Class<T> clz) {
        return mBus.ofType(clz);
    }

    public void unregisterAll() {
        //解除注册
        mBus.onComplete();
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
