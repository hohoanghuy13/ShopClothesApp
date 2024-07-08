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
import com.example.shopclothesapp.databinding.LayoutItemSliderBinding;
import com.example.shopclothesapp.repositories.Slider;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    List<Slider> sliders;

    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemSliderBinding itemSliderBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_slider, parent, false);
        return new SliderViewHolder(itemSliderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Slider slider = sliders.get(position);

        holder.itemSliderBinding.setSliderViewHolder(slider);
    }

    @Override
    public int getItemCount() {
        return sliders != null ? sliders.size() : 0;
    }


    public class SliderViewHolder extends RecyclerView.ViewHolder {
        LayoutItemSliderBinding itemSliderBinding;
        public SliderViewHolder(@NonNull LayoutItemSliderBinding itemSliderBinding) {
            super(itemSliderBinding.getRoot());

            this.itemSliderBinding = itemSliderBinding;
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
