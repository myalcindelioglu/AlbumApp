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
import java.util.Observable;

import io.reactivex.Single;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class PhotoDetailViewModel extends Observable
        implements BaseViewModel<Photo> {

    public static final String SELECTED_PHOTO_BUNDLE_TEXT = "SELECTED_PHOTO_BUNDLE_TEXT";
    private Photo photo;

    public ObservableField<String> photoIdText;
    public ObservableField<String> photoAlbumIdText;
    public ObservableField<String> photoTitleText;

    public PhotoDetailViewModel(Photo photo) {
        this.photo = photo;
        photoIdText = new ObservableField<>(String.valueOf(photo.getId()));
        photoAlbumIdText = new ObservableField<>(String.valueOf(photo.getAlbumId()));
        photoTitleText = new ObservableField<>(photo.getTitle());
    }

    public String getPhotoUrl() {
        return photo.getUrl();
    }

    @BindingAdapter("imageUrl") public static void setPhotoUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @Override
    public Single<List<Photo>> fetch() {
        throw new UnsupportedOperationException();
    }
}
