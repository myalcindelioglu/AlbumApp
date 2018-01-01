package com.myd.albumapp.app;

import android.app.Application;

import com.myd.albumapp.data.AlbumService;
import com.myd.albumapp.data.DataFactory;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class AlbumApplication extends Application {
    private AlbumService albumService;

    @Override
    public void onCreate() {
        super.onCreate();
        albumService = DataFactory.create();
    }

    public AlbumService getAlbumService() {
        return albumService;
    }
}
