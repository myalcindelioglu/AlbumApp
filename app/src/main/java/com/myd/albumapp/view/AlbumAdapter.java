package com.myd.albumapp.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myd.albumapp.R;
import com.myd.albumapp.databinding.ItemAlbumBinding;
import com.myd.albumapp.model.Album;
import com.myd.albumapp.viewmodel.AlbumItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumAdapterViewHolder> {

    private List<Album> albumList;

    AlbumAdapter() {
        this.albumList = Collections.emptyList();
    }

    void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        this.notifyDataSetChanged();
    }

    @Override
    public AlbumAdapter.AlbumAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAlbumBinding itemAlbumBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_album, parent, false);
        return new AlbumAdapterViewHolder(itemAlbumBinding);
    }

    @Override
    public void onBindViewHolder(AlbumAdapter.AlbumAdapterViewHolder holder, int position) {
        holder.bindAlbum(albumList.get(position));
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    static class AlbumAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemAlbumBinding itemAlbumBinding;
        AlbumAdapterViewHolder(ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.itemAlbum);
            this.itemAlbumBinding = itemAlbumBinding;
        }

        void bindAlbum(Album album) {
            if (itemAlbumBinding.getAlbumItemViewModel() == null) {
                itemAlbumBinding.setAlbumItemViewModel(new AlbumItemViewModel(itemView.getContext(), album));
            } else {
                itemAlbumBinding.getAlbumItemViewModel().setAlbum(album);
            }
        }
    }
}
