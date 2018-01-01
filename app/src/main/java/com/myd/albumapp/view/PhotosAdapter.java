package com.myd.albumapp.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myd.albumapp.R;
import com.myd.albumapp.databinding.ItemPhotoBinding;
import com.myd.albumapp.model.Photo;
import com.myd.albumapp.viewmodel.PhotoItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by MYD on 12/17/17.
 *
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoAdapterViewHolder> {

    private List<Photo> photoList;

    PhotosAdapter() {
        this.photoList = Collections.emptyList();
    }

    void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        this.notifyDataSetChanged();
    }

    @Override
    public PhotosAdapter.PhotoAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPhotoBinding itemPhotoBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_photo, parent, false);
        return new PhotoAdapterViewHolder(itemPhotoBinding);
    }

    @Override
    public void onBindViewHolder(PhotosAdapter.PhotoAdapterViewHolder holder, int position) {
        holder.bindPhoto(photoList.get(position));
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class PhotoAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPhotoBinding itemPhotoBinding;
        PhotoAdapterViewHolder(ItemPhotoBinding itemPhotoBinding) {
            super(itemPhotoBinding.itemPhoto);
            this.itemPhotoBinding = itemPhotoBinding;
        }

        void bindPhoto(Photo photo) {
            if (itemPhotoBinding.getPhotoItemViewModel() == null) {
                itemPhotoBinding.setPhotoItemViewModel(new PhotoItemViewModel(itemView.getContext(), photo));
            } else {
                itemPhotoBinding.getPhotoItemViewModel().setPhoto(photo);
            }
        }
    }
}
