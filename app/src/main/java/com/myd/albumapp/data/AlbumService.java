package com.myd.albumapp.data;

import com.myd.albumapp.model.Album;
import com.myd.albumapp.model.Photo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MYD on 12/17/17.
 *
 */

public interface AlbumService {

    @GET("/albums?userId=1")
    Single<List<Album>> fetchAlbums();

    @GET("/photos")
    Single<List<Photo>> fetchPhotos(
            @Query("albumId") int albumId
    );
}
