package com.example.bemobiletest.dagger;

import com.example.bemobiletest.constants.Constants;
import com.example.bemobiletest.networking.retrofitservices.GnbTradesRetrofitService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

    @Provides
    Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

   @Provides
   GnbTradesRetrofitService getGnbTrades(){
        return getRetrofit(Constants.BASE_URL).create(GnbTradesRetrofitService.class);
   }

}
