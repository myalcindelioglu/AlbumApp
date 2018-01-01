package com.myd.albumapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myd.albumapp.model.Photo;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class PhotoItemViewModel extends BaseObservable
        implements BaseViewModel<Photo> {

    private static final String TAG = "PhotoItemViewModel";

    private Context context;
    private Photo photo;

    public ObservableInt photoItemVisibility;
    public ObservableField<String> photoItemIdText;
    public ObservableField<String> photoItemTitleText;

    public PhotoItemViewModel(Context context, Photo photo) {
        this.context = context;
        this.photo = photo;
        this.photoItemVisibility = new ObservableInt(View.VISIBLE);
        this.photoItemIdText = new ObservableField<>(String.valueOf(photo.getId()));
        this.photoItemTitleText = new ObservableField<>(photo.getTitle());
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
        this.photoItemVisibility.set(View.VISIBLE);
        this.photoItemIdText.set(String.valueOf(photo.getId()));
        this.photoItemTitleText.set(photo.getTitle());
    }

    public String getThumbnailUrl() {
        return photo.getThumbnailUrl();
    }

    @BindingAdapter("thumbnailUrl") public static void setThumbnailUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @SuppressWarnings("unused")
    public void onClickItem(View view) {
        Log.d(TAG, photo.getId() + " is clicked");
    }

    @Override
    public Single<List<Photo>> fetch() {
        throw new UnsupportedOperationException();
    }
}
