package com.moeiny.reza.servian_test.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


/*
 * this object is essential to showing image on ImageView for viewitems of ViewAdapter
    when we use databinding
*/

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }
}