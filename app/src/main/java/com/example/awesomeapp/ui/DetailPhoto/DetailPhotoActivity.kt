package com.example.awesomeapp.ui.DetailPhoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.awesomeapp.R
import com.example.awesomeapp.databinding.ActivityDetailPhotoBinding
import com.example.awesomeapp.databinding.ActivityListPhotoBinding
import com.example.awesomeapp.model.PhotosItem
import com.example.awesomeapp.utils.Utility.checkIsNull
import com.example.awesomeapp.utils.Utility.formatUsername
import com.example.awesomeapp.utils.Utility.loadImage

class DetailPhotoActivity : AppCompatActivity() {

    private var _activityDetailPhoto: ActivityDetailPhotoBinding? = null
    private var photosItem: PhotosItem? = null
    private val binding get() = _activityDetailPhoto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailPhoto = ActivityDetailPhotoBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        photosItem = intent.getParcelableExtra(EXTRA_DETAIL)
        binding?.apply {
            ivPhoto.loadImage(photosItem?.src?.large)
            tvId.text = checkIsNull(photosItem?.id.toString())
            tvName.text = checkIsNull(photosItem?.photographer)
            tvUsername.text = checkIsNull(formatUsername(photosItem?.photographerUrl))
            tvWidthNum.text = photosItem?.width.toString()
            tvHeightNum.text = photosItem?.height.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailPhoto = null
    }

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
    }
}