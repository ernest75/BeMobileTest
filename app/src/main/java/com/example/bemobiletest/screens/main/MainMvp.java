package com.example.bemobiletest.screens.main;

import android.content.Intent;

import com.example.bemobiletest.models.Product;
import com.example.bemobiletest.models.Rate;
import com.example.bemobiletest.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MainMvp {

    interface View{

        void showData(List<Product> products);

        void showErrorFromNetwork(String messageError);

    }

    interface Presenter{

        void setView(MainMvp.View view);

        void getRates();

        void getTransactions();

        void onProductClicked(int position);

        List<String> getProductName(List<Product> products, ArrayList<String> productName);

        void clearStreams();

    }

    interface Model{

        void storeRates(List<Rate> rates);

        List<Product> getProductsFromTransactions(List<Transaction> transactions);



    }

}
