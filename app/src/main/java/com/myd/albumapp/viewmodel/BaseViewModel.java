package com.myd.albumapp.viewmodel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by MYD on 12/16/17.
 *
 */

public interface BaseViewModel<T> {
    Single<List<T>> fetch();
}
