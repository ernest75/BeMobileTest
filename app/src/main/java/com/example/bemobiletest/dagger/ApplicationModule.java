package com.example.bemobiletest.dagger;

import android.app.Application;
import android.content.Context;

import com.example.bemobiletest.screens.detail.DetailMvp;
import com.example.bemobiletest.screens.detail.DetailPresenter;
import com.example.bemobiletest.screens.main.MainModel;
import com.example.bemobiletest.screens.main.MainMvp;
import com.example.bemobiletest.screens.main.MainPresenter;
import com.example.bemobiletest.networking.retrofitservices.GnbTradesRetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }

    @Provides
    MainMvp.Presenter providesMainPresenter(GnbTradesRetrofitService gnbTradesRetrofitService){
        return new MainPresenter(mApplication, gnbTradesRetrofitService,provideMainModel());
    }

    @Provides
    MainMvp.Model provideMainModel(){
        return new MainModel();
    }

    @Provides
    DetailMvp.Presenter provideDetailPresenter(){
        return new DetailPresenter();
    }


}
