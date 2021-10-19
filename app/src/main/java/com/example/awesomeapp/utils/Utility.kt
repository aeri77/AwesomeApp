package com.example.awesomeapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.awesomeapp.R

/**
 * @author Aeri on 10/19/2021.
 */
object Utility {

    fun checkIsNull(str: String?): String {
        return if (str.isNullOrBlank()) "....." else str
    }

    fun formatUsername(str: String?): String {
        val pexelUrl = "https://www.pexels.com/"
        return checkIsNull(str?.removeRange(0, pexelUrl.length))
    }

    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().override(320, 320))
            .error(R.drawable.ic_image_broken)
            .centerInside()
            .placeholder(R.drawable.ic_image_placeholder)
            .into(this)
    }

    fun setIcon(context: Context, resIdDrawable: Int, resIdColor: Int): Drawable {
        val icon = DrawableCompat.wrap(
            ContextCompat.getDrawable(
                context, resIdDrawable)!!
        )
        icon.setTint(
            ContextCompat.getColor(
                context,
                resIdColor)
        )
        return icon
    }
}