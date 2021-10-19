package com.example.awesomeapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.awesomeapp.databinding.ItemRowPhotoGridBinding
import com.example.awesomeapp.databinding.ItemRowPhotoLinearBinding
import com.example.awesomeapp.model.PhotosResponse
import com.example.awesomeapp.utils.Utility.checkIsNull
import com.example.awesomeapp.utils.Utility.formatUsername
import com.example.awesomeapp.utils.Utility.loadImage

/**
 * @author Aeri on 10/19/2021.
 */

class ListPhotoAdapter(private val photosResponse: PhotosResponse, private val layoutType: Int) : RecyclerView.Adapter<ListPhotoAdapter.ListViewHolder>()  {

    inner class ListViewHolder(val binding: ViewBinding ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPhotoAdapter.ListViewHolder {
        return if(layoutType == GRID) {
            val binding = ItemRowPhotoGridBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            ListViewHolder(binding)
        } else {
            val binding = ItemRowPhotoLinearBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            ListViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ListPhotoAdapter.ListViewHolder, position: Int) {
        when(holder.binding){
            is ItemRowPhotoLinearBinding -> {
                holder.binding.apply {
                    tvName.text = checkIsNull(photosResponse.photos?.get(position)?.photographer)
                    tvHeightNum.text = checkIsNull(photosResponse.photos?.get(position)?.height.toString())
                    tvWidthNum.text = checkIsNull(photosResponse.photos?.get(position)?.width.toString())
                    tvUsername.text = formatUsername(photosResponse.photos?.get(position)?.photographerUrl)
                    ivPhoto.loadImage(photosResponse.photos?.get(position)?.src?.medium)
                }
            }
            is ItemRowPhotoGridBinding -> {
                holder.binding.apply {
                    tvName.text = checkIsNull(photosResponse.photos?.get(position)?.photographer)
                    tvHeightNum.text = checkIsNull(photosResponse.photos?.get(position)?.height.toString())
                    tvWidthNum.text = checkIsNull(photosResponse.photos?.get(position)?.width.toString())
                    tvUsername.text = formatUsername(photosResponse.photos?.get(position)?.photographerUrl)
                    ivPhoto.loadImage(photosResponse.photos?.get(position)?.src?.medium)
                }
            }
        }
    }

    override fun getItemCount(): Int = photosResponse.photos?.size ?: 0

    companion object {
        const val LINEAR = 1
        const val GRID = 2
    }
}