package com.example.bemobiletest.screens.detail;

import android.content.Intent;

import com.example.bemobiletest.models.Product;

public interface DetailMvp {

    interface View{

        void configView(Product product);

        Intent getIntentCreator();

    }

    interface Presenter{

        void setView(DetailMvp.View view);

        void showData();

        String showTransactions(Product product);

    }
}
