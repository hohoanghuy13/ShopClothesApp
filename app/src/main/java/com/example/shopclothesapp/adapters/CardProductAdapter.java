package com.example.shopclothesapp.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopclothesapp.R;
import com.example.shopclothesapp.databinding.ItemProductBinding;
import com.example.shopclothesapp.viewmodels.CardProductViewModel;

import java.util.List;

public class CardProductAdapter extends RecyclerView.Adapter<CardProductAdapter.CardProductViewHolder> {
    List<CardProductViewModel> cardProductViewModels;

    public void setCardProductViewModels(List<CardProductViewModel> cardProductViewModels) {
        this.cardProductViewModels = cardProductViewModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding itemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product, parent, false);
        return new CardProductViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardProductViewHolder holder, int position) {
        CardProductViewModel cardProductViewModel = cardProductViewModels.get(position);

        holder.itemProductBinding.setCardProductViewModel(cardProductViewModel);
    }

    @Override
    public int getItemCount() {
        return cardProductViewModels != null ? cardProductViewModels.size() : 0;
    }

    public class CardProductViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding itemProductBinding;
        public CardProductViewHolder(@NonNull ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
        }
    }
    @BindingAdapter({"imageUrl", "imageError"})
    public static void setImageUrl(ImageView imageView, String urlImage, Drawable errorImage) {
        //Bo goc hinh anh
        RequestOptions requestOptions = new RequestOptions().transform(new RoundedCorners(16));

        Glide.with(imageView.getContext())
                .load(urlImage)
                .error(errorImage)
                .apply(requestOptions)
                .into(imageView);
    }
}
