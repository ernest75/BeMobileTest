package com.example.bemobiletest.screens.main;

import android.content.Context;
import android.content.Intent;

import com.example.bemobiletest.constants.Constants;
import com.example.bemobiletest.models.Product;
import com.example.bemobiletest.models.Rate;
import com.example.bemobiletest.models.Transaction;
import com.example.bemobiletest.screens.detail.Detail;
import com.example.bemobiletest.networking.retrofitservices.GnbTradesRetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainMvp.Presenter {

    private static final String LOG_TAG = MainPresenter.class.getSimpleName();

    private MainMvp.View view;

    private MainMvp.Model model;

    private Context context;

    private GnbTradesRetrofitService gnbTradesRetrofitService;

    private List<Product> productsList = new ArrayList<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenter(Context context, GnbTradesRetrofitService gnbTradesRetrofitService, MainMvp.Model model) {
        this.context = context;
        this.gnbTradesRetrofitService = gnbTradesRetrofitService;
        this.model = model;
    }

    @Override
    public void setView(MainMvp.View view) {
        this.view = view;
    }

    @Override
    public void getRates() {
        compositeDisposable.add(gnbTradesRetrofitService.getRatesFromNetwork()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Rate>>() {
                    @Override
                    public void onNext(List<Rate> rates) {
                        model.storeRates(rates);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorFromNetwork(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void getTransactions() {
        compositeDisposable.add(gnbTradesRetrofitService.getTransactionsFrtomNetwork()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Transaction>>() {
                    @Override
                    public void onNext(List<Transaction> transactions) {
                        productsList = model.getProductsFromTransactions(transactions);
                        view.showData(productsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorFromNetwork(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    public void onProductClicked(int position) {
        Intent intent = new Intent(context, Detail.class);
        Product product = productsList.get(position);
        intent.putExtra(Constants.INTENT_DETAIL_PRODUCT_KEY, product);
        context.startActivity(intent);
    }

    @Override
    public List<String> getProductName(List<Product> products, ArrayList<String> productName) {
        for (int i = 0; i < products.size(); i++) {
            productName.add(products.get(i).getId());
        }
        return productName;
    }

    @Override
    public void clearStreams() {
        compositeDisposable.clear();
    }


}
