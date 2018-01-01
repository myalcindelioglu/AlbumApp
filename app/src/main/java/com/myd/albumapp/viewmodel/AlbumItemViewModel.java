package com.myd.albumapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.myd.albumapp.model.Album;
import com.myd.albumapp.view.PhotoListActivity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class AlbumItemViewModel extends BaseObservable
        implements BaseViewModel<Album> {

    private static final String TAG = "AlbumItemViewModel";

    private Context context;
    private Album album;

    public ObservableInt albumItemTitleVisibility;
    public ObservableField<String> albumItemTitleText;

    public AlbumItemViewModel(Context context, Album album) {
        this.context = context;
        this.album = album;
        this.albumItemTitleVisibility = new ObservableInt(View.VISIBLE);
        this.albumItemTitleText = new ObservableField<>(album.getTitle());
    }

    public void setAlbum(Album album) {
        this.album = album;
        this.albumItemTitleVisibility = new ObservableInt(View.VISIBLE);
        this.albumItemTitleText = new ObservableField<>(album.getTitle());
    }

    @SuppressWarnings("unused")
    public void onClickItem(View view) {
        Log.d(TAG, album.getId() + " is clicked");
        Intent photoListIntent = new Intent(context, PhotoListActivity.class);
        photoListIntent.putExtra(
                PhotoListViewModel.SELECTED_ITEM_BUNDLE_TEXT,
                album.getId());
        context.startActivity(photoListIntent);
    }

    @Override
    public Single<List<Album>> fetch() {
        throw new UnsupportedOperationException();
    }
}
