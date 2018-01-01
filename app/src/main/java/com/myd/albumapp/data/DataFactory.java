package com.myd.albumapp.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class DataFactory {
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static AlbumService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(AlbumService.class);
    }
}
