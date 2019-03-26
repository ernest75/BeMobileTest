package com.example.bemobiletest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bemobiletest.R;
import com.example.bemobiletest.screens.main.MainMvp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {


    private static final String LOG_TAG = RvAdapter.class.getSimpleName();
    private List<String> productList;
    private Context context;
    private MainMvp.Presenter presenter;

    public RvAdapter(List<String> productList, Context context, MainMvp.Presenter presenter) {
        this.productList = productList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final String productName = productList.get(i);
        viewHolder.tvProduct.setText(productName);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.onProductClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ItemClickListener itemClickListener;

        @BindView(R.id.tvProduct)
        TextView tvProduct;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition());
        }
    }

    public interface ItemClickListener {

        void onClick (View view, int position);
    }
}
