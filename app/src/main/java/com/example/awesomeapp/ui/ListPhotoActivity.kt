package com.example.awesomeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeapp.R
import com.example.awesomeapp.databinding.ActivityListPhotoBinding
import com.example.awesomeapp.model.PhotosResponse
import com.example.awesomeapp.utils.Utility.setIcon

class ListPhotoActivity : AppCompatActivity() {

    private val listPhotoViewModel by viewModels<ListPhotoViewModel>()
    private var _activityListPhotoBinding: ActivityListPhotoBinding? = null
    private val binding get() = _activityListPhotoBinding
    private var rvPhotos: RecyclerView? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_option, menu)
        listPhotoViewModel.listViewState.observe(this,{
            when(it){
                ListPhotoAdapter.LINEAR -> {
                    menu?.findItem(R.id.menu_linear_view)?.apply {
                        icon = setIcon(this@ListPhotoActivity, R.drawable.ic_list_linear, R.color.secondaryDarkColor)
                    }
                    menu?.findItem(R.id.menu_grid_view)?.apply {
                        icon = setIcon(this@ListPhotoActivity, R.drawable.ic_list_grid, R.color.off_color)
                    }
                }
                ListPhotoAdapter.GRID -> {
                    menu?.findItem(R.id.menu_grid_view)?.apply {
                        icon = setIcon(this@ListPhotoActivity, R.drawable.ic_list_grid, R.color.secondaryDarkColor)
                    }
                    menu?.findItem(R.id.menu_linear_view)?.apply {
                        icon = setIcon(this@ListPhotoActivity, R.drawable.ic_list_linear, R.color.off_color)
                    }
                }
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityListPhotoBinding = ActivityListPhotoBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        rvPhotos = binding?.rvListPhoto
        listPhotoViewModel.getAllPhotos()
        listPhotoViewModel.setListviewState(ListPhotoAdapter.LINEAR)
        listPhotoViewModel.listPhotoResponse.observe(this, {
            showRecycler(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_linear_view -> {
                listPhotoViewModel.setListviewState(ListPhotoAdapter.LINEAR)
            }
            R.id.menu_grid_view -> {
                listPhotoViewModel.setListviewState(ListPhotoAdapter.GRID)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecycler(photos: PhotosResponse) {
        listPhotoViewModel.listViewState.observe(this, {
            when (it) {
                ListPhotoAdapter.LINEAR -> rvPhotos?.layoutManager = LinearLayoutManager(this)
                ListPhotoAdapter.GRID -> rvPhotos?.layoutManager = GridLayoutManager(this, 2)
            }
            rvPhotos?.adapter = ListPhotoAdapter(photos, it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityListPhotoBinding = null
    }
}