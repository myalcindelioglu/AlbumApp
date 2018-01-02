package com.myd.albumapp.model;

import java.io.Serializable;

/**
 * Created by MYD on 12/18/17.
 *
 */

public class Photo implements BasePhoto, Serializable {

    private int id;
    private int albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getAlbumId() {
        return albumId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
