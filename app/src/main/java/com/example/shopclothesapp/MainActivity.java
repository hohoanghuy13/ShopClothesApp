package com.example.shopclothesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.shopclothesapp.adapters.SliderAdapter;
import com.example.shopclothesapp.databinding.ActivityMainBinding;
import com.example.shopclothesapp.repositories.Slider;
import com.example.shopclothesapp.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    ActivityMainBinding mainBinding;
    SliderAdapter sliderAdapter;
    List<Slider> currentSliders = new ArrayList<>();
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(mainBinding.vpBanner.getCurrentItem() == currentSliders.size() - 1) {
                mainBinding.vpBanner.setCurrentItem(0);
            } else {
                mainBinding.vpBanner.setCurrentItem(mainBinding.vpBanner.getCurrentItem() + 1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainBinding.setLifecycleOwner(this);
        mainBinding.setMainViewModel(mainViewModel);

        sliderAdapter = new SliderAdapter();
        mainViewModel.getSliderLiveData().observe(this, sliders -> currentSliders = sliders);

        currentSliders.add(new Slider("https://alabaster-drop-b52.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F5fd31f3f-d417-4239-a99d-6bd3d5633d38%2F54bd0584-aa75-4c4c-8b66-f11053a228f6%2Fmovie_webview_basic_java.png?table=block&id=6a302749-6e34-4571-894a-c60ea2bb6f79&spaceId=5fd31f3f-d417-4239-a99d-6bd3d5633d38&width=2000&userId=&cache=v2"));
        currentSliders.add(new Slider("https://alabaster-drop-b52.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F5fd31f3f-d417-4239-a99d-6bd3d5633d38%2F51b5916d-a737-4a6d-b593-9bb6b6597f9a%2Fmovie_webview_pro_java.png?table=block&id=3178d203-e678-4e44-a951-994850cc81af&spaceId=5fd31f3f-d417-4239-a99d-6bd3d5633d38&width=2000&userId=&cache=v2"));
        currentSliders.add(new Slider("https://alabaster-drop-b52.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F5fd31f3f-d417-4239-a99d-6bd3d5633d38%2F2e14356d-7bfd-4f0b-b5ff-81d76b9e980a%2Fyoutube_channel_java.png?table=block&id=b1d3f9ff-6d35-4b01-9515-6a6cba700a4b&spaceId=5fd31f3f-d417-4239-a99d-6bd3d5633d38&width=2000&userId=&cache=v2"));
        currentSliders.add(new Slider("https://alabaster-drop-b52.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F5fd31f3f-d417-4239-a99d-6bd3d5633d38%2F4be05907-0112-4012-8f26-0ee35cdaac29%2FMusic_Basic_Java.png?table=block&id=456c5af3-1161-416f-835c-9678ba2d64c8&spaceId=5fd31f3f-d417-4239-a99d-6bd3d5633d38&width=2000&userId=&cache=v2"));

        sliderAdapter.setSliders(currentSliders);
        mainBinding.vpBanner.setAdapter(sliderAdapter);
        mainBinding.circleIndicator3.setViewPager(mainBinding.vpBanner);
        sliderAdapter.registerAdapterDataObserver(mainBinding.circleIndicator3.getAdapterDataObserver());
        for(int i = 0; i < mainBinding.circleIndicator3.getChildCount(); i++){
            final int position = i;
            View viewIndicator = mainBinding.circleIndicator3.getChildAt(i);
            viewIndicator.setOnClickListener(view -> mainBinding.vpBanner.setCurrentItem(position));
        }

        mainViewModel.setSliders(currentSliders);

        mainBinding.vpBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 5000);
            }
        });

        mainBinding.vpBanner.setPageTransformer(new ZoomOutPageTransformer());
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 5000);
    }
}