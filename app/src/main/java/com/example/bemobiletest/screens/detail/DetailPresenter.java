package com.example.bemobiletest.screens.detail;

import com.example.bemobiletest.constants.Constants;
import com.example.bemobiletest.models.Product;
import com.example.bemobiletest.models.Transaction;

import java.util.ArrayList;

public class DetailPresenter implements DetailMvp.Presenter {

    private DetailMvp.View view;


    @Override
    public void setView(DetailMvp.View view) {
        this.view = view;
    }

    @Override
    public void showData() {
        view.configView(getProduct());
    }

    private Product getProduct() {
        return (Product)view.getIntentCreator().getSerializableExtra(Constants.INTENT_DETAIL_PRODUCT_KEY);

    }

    public String showTransactions(Product product) {
        ArrayList<Transaction> transactions = product.getTransactions();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < transactions.size(); i++) {
            String amount = String.valueOf(transactions.get(i).getAmount());
            String currency = String.valueOf(transactions.get(i).getCurrency());
            sb.append(amount + " " + currency + "\n");
        }
        return sb.toString();
    }
}
