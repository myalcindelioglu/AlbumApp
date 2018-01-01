package com.myd.albumapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myd.albumapp.R;
import com.myd.albumapp.databinding.ActivityMainBinding;
import com.myd.albumapp.viewmodel.MainViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity
        implements Observer {

    private ActivityMainBinding mainActivityBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setAlbumListView(mainActivityBinding.listAlbum);
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainActivityBinding.setMainViewModel(mainViewModel);
        mainViewModel.addObserver(this);
    }

    private void setAlbumListView(RecyclerView listAlbum) {
        AlbumAdapter albumAdapter = new AlbumAdapter();
        listAlbum.setAdapter(albumAdapter);
        listAlbum.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration dividerItemDecoration =
                new ItemDividerDecoration(getDrawable(R.drawable.divider_drawable));
        listAlbum.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof MainViewModel) {
            AlbumAdapter adapter = (AlbumAdapter) mainActivityBinding.listAlbum.getAdapter();
            MainViewModel mainViewModel = (MainViewModel)observable;
            adapter.setAlbumList(mainViewModel.getAlbumList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }
}
