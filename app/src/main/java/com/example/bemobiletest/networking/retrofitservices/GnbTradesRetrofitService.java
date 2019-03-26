package com.example.bemobiletest.networking.retrofitservices;


import com.example.bemobiletest.models.Rate;
import com.example.bemobiletest.models.Transaction;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GnbTradesRetrofitService {

    @GET("transactions.json")
    Observable<List<Transaction>> getTransactionsFrtomNetwork();

    @GET("rates.json")
    Observable<List<Rate>> getRatesFromNetwork();

}
