package com.example.awesomeapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.awesomeapp.databinding.ItemRowPhotoGridBinding
import com.example.awesomeapp.databinding.ItemRowPhotoLinearBinding
import com.example.awesomeapp.model.PhotosItem
import com.example.awesomeapp.model.PhotosResponse
import com.example.awesomeapp.utils.Utility.checkIsNull
import com.example.awesomeapp.utils.Utility.formatUsername
import com.example.awesomeapp.utils.Utility.loadImage

/**
 * @author Aeri on 10/19/2021.
 */

class ListPhotoAdapter(private val photosResponse: PhotosResponse, private val layoutType: Int) : RecyclerView.Adapter<ListPhotoAdapter.ListViewHolder>()  {

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewHolder(val binding: ViewBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        return if(layoutType == GRID) {
            val binding = ItemRowPhotoGridBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            ListViewHolder(binding)
        } else {
            val binding = ItemRowPhotoLinearBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            ListViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        when(holder.binding){
            is ItemRowPhotoLinearBinding -> {
                holder.binding.apply {
                    tvName.text = checkIsNull(photosResponse.photos?.get(position)?.photographer)
                    tvHeightNum.text = checkIsNull(photosResponse.photos?.get(position)?.height.toString())
                    tvWidthNum.text = checkIsNull(photosResponse.photos?.get(position)?.width.toString())
                    tvUsername.text = formatUsername(photosResponse.photos?.get(position)?.photographerUrl)
                    ivPhoto.loadImage(photosResponse.photos?.get(position)?.src?.medium, 320, 320)
                    cardView.setOnClickListener { onItemClickCallback.onItemClicked(photosResponse.photos?.get(position)) }
                }
            }
            is ItemRowPhotoGridBinding -> {
                holder.binding.apply {
                    tvName.text = checkIsNull(photosResponse.photos?.get(position)?.photographer)
                    tvHeightNum.text = checkIsNull(photosResponse.photos?.get(position)?.height.toString())
                    tvWidthNum.text = checkIsNull(photosResponse.photos?.get(position)?.width.toString())
                    tvUsername.text = formatUsername(photosResponse.photos?.get(position)?.photographerUrl)
                    ivPhoto.loadImage(photosResponse.photos?.get(position)?.src?.medium, 320, 320)
                    cardView.setOnClickListener { onItemClickCallback.onItemClicked(photosResponse.photos?.get(position)) }
                }
            }
        }
    }

    override fun getItemCount(): Int = photosResponse.photos?.size ?: 0

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(get: PhotosItem?)
    }

    companion object {
        const val LINEAR = 1
        const val GRID = 2
    }

}