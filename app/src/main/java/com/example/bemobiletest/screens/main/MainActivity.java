package com.example.bemobiletest.screens.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.bemobiletest.models.Product;
import com.example.bemobiletest.R;
import com.example.bemobiletest.adapters.RvAdapter;
import com.example.bemobiletest.dagger.App;
import com.example.bemobiletest.networking.retrofitservices.GnbTradesRetrofitService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMvp.View {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Inject
    Context context;

    @Inject
    MainMvp.Presenter presenter;

    @Inject
    GnbTradesRetrofitService gnbTradesRetrofitService;

    @BindView(R.id.rvProducts)
    android.support.v7.widget.RecyclerView rvProducts;

    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //di's
        ButterKnife.bind(this);
        ((App) getApplication()).getApplicationComponent().inject(this);

        configView();
    }

    private void configView() {
        presenter.setView(this);
        presenter.getRates();
        presenter.getTransactions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clearStreams();
        presenter.setView(null);
    }

    @Override
    public void showData(List<Product> products) {
        ArrayList<String> productName = new ArrayList<>();
        presenter.getProductName(products, productName);
        adapter = new RvAdapter(productName,context, presenter);
        rvProducts.setLayoutManager(new LinearLayoutManager(context));
        rvProducts.setAdapter(adapter);
    }

    @Override
    public void showErrorFromNetwork(String messageError) {
        Log.e(LOG_TAG, messageError);
    }


}
