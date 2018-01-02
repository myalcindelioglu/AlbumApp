package com.myd.albumapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.myd.albumapp.R;
import com.myd.albumapp.databinding.ActivityPhotoDetailBinding;
import com.myd.albumapp.model.Photo;
import com.myd.albumapp.viewmodel.PhotoDetailViewModel;

/**
 * Created by MYD on 1/2/18.
 *
 */

public class PhotoDetailActivity extends AppCompatActivity {

    private ActivityPhotoDetailBinding photoDetailActBinding;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(photoDetailActBinding.activityPhotoDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initDataBinding() {
        photoDetailActBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail);
        PhotoDetailViewModel photoDetailViewModel = new PhotoDetailViewModel(getPhotoFromExtra());
        photoDetailActBinding.setPhotoDetailViewModel(photoDetailViewModel);
    }

    private Photo getPhotoFromExtra() {
        Bundle extras = this.getIntent().getExtras();
        return extras == null ?  new Photo():
                (Photo) extras.getSerializable(
                        PhotoDetailViewModel.SELECTED_PHOTO_BUNDLE_TEXT);
    }
}
