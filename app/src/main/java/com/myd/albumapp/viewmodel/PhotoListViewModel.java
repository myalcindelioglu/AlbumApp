package com.myd.albumapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.myd.albumapp.R;
import com.myd.albumapp.app.AlbumApplication;
import com.myd.albumapp.model.Photo;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MYD on 12/18/17.
 *
 */

public class PhotoListViewModel extends Observable implements BaseViewModel<Photo>{

    public static final String SELECTED_ITEM_BUNDLE_TEXT = "SELECTED_ITEM_BUNDLE_TEXT";

    public ObservableInt photoListProgressVisibility;
    public ObservableInt photoListEmptyLabelVisibility;
    public ObservableInt photoListVisibility;

    public ObservableField<String> photoListEmptyLabelText;

    private List<Photo> photoList;

    private Context context;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int selectedAlbumId;

    public PhotoListViewModel(@NonNull Context context, int selectedAlbumId) {
        this.context = context;
        this.selectedAlbumId = selectedAlbumId;
        setViewsForLoading();
        updateList(fetch());
    }

    private void updateList(Single<List<Photo>> singleAlbumList) {
        Disposable disposable = singleAlbumList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> {
                    photoList = photos;
                    setChanged();
                    notifyObservers();
                    if (!photoList.isEmpty()) {
                        setViewsForLoaded();
                    } else {
                        setViewsForEmpty();
                    }
                }, throwable -> setViewsForError());
        compositeDisposable.add(disposable);
    }

    private void setViewsForLoading() {
        photoListEmptyLabelText = new ObservableField<>();
        photoListEmptyLabelVisibility = new ObservableInt(View.GONE);
        photoListVisibility = new ObservableInt(View.GONE);
        photoListProgressVisibility = new ObservableInt(View.VISIBLE);
    }

    private void setViewsForLoaded() {
        photoListEmptyLabelVisibility.set(View.GONE);
        photoListVisibility.set(View.VISIBLE);
        photoListProgressVisibility.set(View.GONE);
    }

    private void setViewsForEmpty() {
        photoListEmptyLabelText.set(context.getString(R.string.album_empty_label_default_text));
        photoListEmptyLabelVisibility.set(View.VISIBLE);
        photoListVisibility.set(View.GONE);
        photoListProgressVisibility.set(View.GONE);
    }

    private void setViewsForError() {
        photoListEmptyLabelText.set(context.getString(R.string.album_empty_label_error_text));
        photoListEmptyLabelVisibility.set(View.VISIBLE);
        photoListVisibility.set(View.GONE);
        photoListProgressVisibility.set(View.GONE);
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    @Override
    public Single<List<Photo>> fetch() {
        AlbumApplication application = (AlbumApplication) context.getApplicationContext();
        return application.getAlbumService().fetchPhotos(selectedAlbumId);
    }

    public void reset() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
