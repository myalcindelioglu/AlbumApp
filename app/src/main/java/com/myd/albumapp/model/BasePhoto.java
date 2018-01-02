package com.myd.albumapp.model;

/**
 * Created by MYD on 12/16/17.
 *
 */

public interface BasePhoto {
    int getId();
    int getAlbumId();
    String getTitle();
    String getUrl();
    String getThumbnailUrl();
}
