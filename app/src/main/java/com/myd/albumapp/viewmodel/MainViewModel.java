package com.myd.albumapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.myd.albumapp.R;
import com.myd.albumapp.app.AlbumApplication;
import com.myd.albumapp.model.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MYD on 12/16/17.
 *
 */

public class MainViewModel extends Observable
        implements BaseViewModel<Album> {

    public ObservableInt albumProgressVisibility;
    public ObservableInt albumEmptyLabelVisibility;
    public ObservableInt albumListVisibility;

    public ObservableField<String> albumEmptyLabelText;

    private List<Album> albumList;

    private Context context;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Context context) {
        this.context = context;
        this.albumList = new ArrayList<>();
        setViewsForDefault();
    }

    @SuppressWarnings("unused")
    public void onClickFab(View view) {
        setViewsForLoading();
        Single<List<Album>> fetch = fetch();
        updateList(fetch);
    }

    private void updateList(Single<List<Album>> singleAlbumList) {
        Disposable disposable = singleAlbumList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(albums -> {
                    albumList = albums;
                    setChanged();
                    notifyObservers();
                    setViewsForLoaded();
                }, throwable -> setViewsForError());
        compositeDisposable.add(disposable);
    }

    private void setViewsForDefault() {
        albumEmptyLabelText = new ObservableField<>(context.getString(R.string.album_empty_label_default_text));
        albumEmptyLabelVisibility = new ObservableInt(View.VISIBLE);
        albumListVisibility = new ObservableInt(View.GONE);
        albumProgressVisibility = new ObservableInt(View.GONE);
    }

    private void setViewsForLoaded() {
        albumEmptyLabelVisibility.set(View.GONE);
        albumListVisibility.set(View.VISIBLE);
        albumProgressVisibility.set(View.GONE);
    }

    private void setViewsForLoading() {
        albumEmptyLabelVisibility.set(View.GONE);
        albumListVisibility.set(View.GONE);
        albumProgressVisibility.set(View.VISIBLE);
    }

    private void setViewsForError() {
        albumEmptyLabelText.set(context.getString(R.string.album_empty_label_error_text));
        albumEmptyLabelVisibility.set(View.VISIBLE);
        albumListVisibility.set(View.GONE);
        albumProgressVisibility.set(View.GONE);
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    @Override
    public Single<List<Album>> fetch() {
        AlbumApplication application = (AlbumApplication) context.getApplicationContext();
        return application.getAlbumService().fetchAlbums();
    }

    public void reset() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
