package com.example.awesomeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeapp.R
import com.example.awesomeapp.databinding.ActivityListPhotoBinding
import com.example.awesomeapp.databinding.ItemRowPhotoLinearBinding
import com.example.awesomeapp.model.PhotosResponse

class ListPhotoActivity : AppCompatActivity() {

    private val listPhotoViewModel by viewModels<ListPhotoViewModel>()
    private var _activityListPhotoBinding: ActivityListPhotoBinding? = null
    private val binding get() = _activityListPhotoBinding
    private var rvPhotos : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityListPhotoBinding = ActivityListPhotoBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        rvPhotos = binding?.rvListPhoto
        Log.e("size", "it created")
        listPhotoViewModel.getAllPhotos()
        listPhotoViewModel.listPhotoResponse.observe(this,{
            showRecycler(it)
        })
    }

    private fun showRecycler(photos: PhotosResponse){
//        rvPhotos?.layoutManager = LinearLayoutManager(this)
        rvPhotos?.layoutManager = GridLayoutManager(this,2)
        rvPhotos?.adapter = ListPhotoAdapter(photos, ListPhotoAdapter.GRID)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityListPhotoBinding = null
    }
}