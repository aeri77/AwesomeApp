package com.example.awesomeapp.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeapp.R
import com.example.awesomeapp.databinding.ActivityListPhotoBinding
import com.example.awesomeapp.model.PhotosItem
import com.example.awesomeapp.model.PhotosResponse
import com.example.awesomeapp.ui.detail.DetailPhotoActivity
import com.example.awesomeapp.utils.Utility.loadImage
import com.example.awesomeapp.utils.Utility.setIcon

class ListPhotoActivity : AppCompatActivity() {

    private val listPhotoViewModel by viewModels<ListPhotoViewModel>()
    private var _activityListPhotoBinding: ActivityListPhotoBinding? = null
    private val binding get() = _activityListPhotoBinding
    private var rvPhotos: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityListPhotoBinding = ActivityListPhotoBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        rvPhotos = binding?.rvListPhoto

        listPhotoViewModel.getAllPhotos()
        listPhotoViewModel.setListviewState(ListPhotoAdapter.LINEAR)
        listPhotoViewModel.listPhotoResponse.observe(this, {
            binding?.rvListPhoto?.visibility = if(it.photos?.isEmpty() == true) View.GONE else View.VISIBLE
            binding?.expandedImage?.loadImage(it.photos?.get((it.photos.indices).random())?.src?.large)
            showRecycler(it)
        })

        onInitToolbar()
        onErrMsg()
        onRefreshLoading()
    }

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

    private fun onRefreshLoading(){
        listPhotoViewModel.isLoading.observe(this, {
            binding?.spList?.isRefreshing = it
        })
        binding?.spList?.setOnRefreshListener {
            listPhotoViewModel.getAllPhotos()
        }
    }

    private fun showRecycler(photos: PhotosResponse) {
        listPhotoViewModel.listViewState.observe(this, {
            when (it) {
                ListPhotoAdapter.LINEAR -> rvPhotos?.layoutManager = LinearLayoutManager(this)
                ListPhotoAdapter.GRID -> rvPhotos?.layoutManager = GridLayoutManager(this, 2)
            }
            val listPhotoAdapter = ListPhotoAdapter(photos, it)
            rvPhotos?.adapter = listPhotoAdapter
            listPhotoAdapter.setOnItemClickCallback(object : ListPhotoAdapter.OnItemClickCallback {
                override fun onItemClicked(get: PhotosItem?) {
                 selectDetailPhoto(get)
                }
            })
        })
    }

    private fun onInitToolbar(){
        setSupportActionBar(binding?.toolbar)
        binding?.collapsingToolbarLayout?.apply {
            setCollapsedTitleTextAppearance(R.style.MainTitleApp_CollapseTitle)
            setExpandedTitleTextAppearance(R.style.MainTitleApp_CollapseTitle_Expaded)
        }
    }

    private fun onErrMsg(){
        listPhotoViewModel.errMsg.observe(this,{
            binding?.tvErrorMessage?.visibility = if(it.isNullOrBlank()) View.GONE else View.VISIBLE
            binding?.rvListPhoto?.visibility = if(it.isNullOrBlank()) View.VISIBLE else View.GONE
            binding?.tvErrorMessage?.text = it
        })
    }

    private fun selectDetailPhoto(photosItem: PhotosItem?) {
        val moveToDetailIntent = Intent(this@ListPhotoActivity, DetailPhotoActivity::class.java)
        moveToDetailIntent.putExtra(DetailPhotoActivity.EXTRA_DETAIL, photosItem)
        startActivity(moveToDetailIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityListPhotoBinding = null
    }
}