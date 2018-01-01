package com.myd.albumapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.myd.albumapp.R;
import com.myd.albumapp.databinding.ActivityPhotoListBinding;
import com.myd.albumapp.viewmodel.PhotoListViewModel;

import java.util.Observable;
import java.util.Observer;

public class PhotoListActivity extends AppCompatActivity
        implements Observer {

    private ActivityPhotoListBinding photoListActBinding;
    private PhotoListViewModel photoListViewModel;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(photoListActBinding.activityPhotoListToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setPhotoListView(photoListActBinding.activityPhotoListList);
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
        photoListActBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list);
        photoListViewModel = new PhotoListViewModel(this, parseSelectedAlbumId());
        photoListActBinding.setPhotoListViewModel(photoListViewModel);
        photoListViewModel.addObserver(this);
    }

    private int parseSelectedAlbumId() {
        Bundle extras = this.getIntent().getExtras();
        return extras == null ? 0 :
                extras.getInt(
                        PhotoListViewModel.SELECTED_ITEM_BUNDLE_TEXT,
                        0);
    }

    private void setPhotoListView(RecyclerView photoListView) {
        PhotosAdapter photosAdapter = new PhotosAdapter();
        photoListView.setAdapter(photosAdapter);
        photoListView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration dividerItemDecoration =
                new ItemDividerDecoration(getDrawable(R.drawable.divider_drawable));
        photoListView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof PhotoListViewModel) {
            PhotosAdapter adapter = (PhotosAdapter) photoListActBinding.activityPhotoListList.getAdapter();
            PhotoListViewModel photoListViewModel = (PhotoListViewModel) observable;
            adapter.setPhotoList(photoListViewModel.getPhotoList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoListViewModel.reset();
    }
}
