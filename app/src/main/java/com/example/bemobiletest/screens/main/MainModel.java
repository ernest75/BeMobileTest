package com.example.bemobiletest.screens.main;

import com.example.bemobiletest.constants.Constants;
import com.example.bemobiletest.models.Product;
import com.example.bemobiletest.models.Rate;
import com.example.bemobiletest.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainModel implements MainMvp.Model {

    private HashMap<String,Float> ratesToEuros = new HashMap<>();
    private String LOG_TAG = MainModel.class.getSimpleName();

    @Override
    public void storeRates(List<Rate> rates) {
        //storing euro rates
        ratesToEuros.put(Constants.CURRENCY_EURO, 1f);
        for (int i = 0; i < rates.size(); i++) {
            Rate rate = rates.get(i);
            if(rate.getTo().equals(Constants.CURRENCY_EURO)){
                ratesToEuros.put(rate.getFrom(),rate.getRate());
            }
        }
        //storing currencies
        ArrayList<String> currencies= new ArrayList<>();
        Rate rate;
        for (int i = 0; i < rates.size(); i++) {
            rate = rates.get(i);
            if(!currencies.contains(rate.getTo())){
                currencies.add(rate.getTo());
            }
        }

        //getting not provided rate to euro
        for (int i = 0; i < currencies.size(); i++) {
            String currency = currencies.get(i);
            if(!ratesToEuros.containsKey(currency)){
                float auxRate = 1;
                for (int j = 0; j < rates.size(); j++) {
                    rate = rates.get(j);
                    if (rate.getFrom().equals(currency) && ratesToEuros.containsKey(rate.getTo())) {
                        auxRate = auxRate * rate.getRate() * ratesToEuros.get(rate.getTo());
                        break;
                    }
                }
                ratesToEuros.put(currency,auxRate);
                rates.add(new Rate(currency,Constants.CURRENCY_EURO,auxRate));
            }
        }

    }

    @Override
    public List<Product> getProductsFromTransactions(List<Transaction> transactions) {
        List<Product> products = new ArrayList<>();
        ArrayList<String> productsId = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            transaction.setRateToEuro(ratesToEuros.get(transaction.getCurrency()));
            String id = transactions.get(i).getSku();

            if(!productsId.contains(id)){
                Product product = new Product(id);
                product.addTransaction(transaction);
                product.setTotalEuros(product.getTotalEuros() + (transaction.getAmount() * transaction.getRateToEuro()));
                products.add(product);
                productsId.add(id);
            }
            else{
                int aux = productsId.indexOf(id);
                products.get(aux).addTransaction(transaction);
                products.get(aux).setTotalEuros(products.get(aux).getTotalEuros() + (transaction.getAmount() * transaction.getRateToEuro()));
            }
        }
        return products;

    }
}
