package com.sweta.marriage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

public class SpecialGift extends FrameLayout {
    public SpecialGift(@NonNull Context context) {
        super(context);
    }

    public SpecialGift(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpecialGift(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SpecialGift(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @BindingAdapter("app:onTheDayOf")
    public static void onTheDayOf(SpecialGift specialGift, String date) {

    }

    @BindingAdapter("app:wishingYou")
    public static void wishingYou(SpecialGift specialGift, String date) {

    }

    @BindingAdapter("app:bestWishesFrom")
    public static void bestWishesFrom(SpecialGift specialGift, String date) {

    }

    @BindingAdapter("app:cheersTo")
    public static void cheersTo(SpecialGift specialGift, String date) {

    }
}
