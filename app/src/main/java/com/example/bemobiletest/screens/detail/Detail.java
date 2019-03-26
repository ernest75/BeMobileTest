package com.example.bemobiletest.screens.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.bemobiletest.R;
import com.example.bemobiletest.constants.Constants;
import com.example.bemobiletest.dagger.App;
import com.example.bemobiletest.models.Product;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail extends AppCompatActivity implements DetailMvp.View {

    @BindView(R.id.transactionsText)
    TextView transactionsText;
    @BindView(R.id.resultText)
    TextView resultText;

    @Inject
    DetailMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((App) getApplication()).getApplicationComponent().inject(this);

        presenter.setView(this);
        presenter.showData();

    }

    @Override
    public void configView(Product product) {
        //presenter show transactions
        transactionsText.setText(presenter.showTransactions(product));
        resultText.append(getString(R.string.total_euro_amount));
        resultText.append(String.valueOf(product.getTotalEuros()));
        resultText.append(getString(R.string.euro_abreviation));
    }

    @Override
    public Intent getIntentCreator() {
        return getIntent();
    }
}
