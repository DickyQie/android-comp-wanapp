package com.zhangqie.module_mine.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zhangqie.base.base_api.viewmodel.BaseViewModel;
import com.zhangqie.base.base_util.CacheUtil;
import com.zhangqie.network.scheduler.IoMainScheduler;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MineViewModel extends BaseViewModel {

    public MutableLiveData<String> cacheData = new MutableLiveData<>();

    public MineViewModel(@NonNull Application application) {
        super(application);
    }


    public void getCache() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            String totalCacheSize = CacheUtil.getTotalCacheSize();
            if (!emitter.isDisposed()){
                emitter.onNext(totalCacheSize);
            }
        }).compose(new IoMainScheduler<>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        cacheData.postValue(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void clearCache() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                CacheUtil.clearAllCache();
                String size = CacheUtil.getTotalCacheSize();
                if (!emitter.isDisposed()) {
                    emitter.onNext(size);
                }
            }
        }).compose(new IoMainScheduler<>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(String size) {
                        cacheData.postValue(size);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
