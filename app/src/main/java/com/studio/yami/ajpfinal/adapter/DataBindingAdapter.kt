package com.studio.yami.ajpfinal.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("poster")
fun ImageView.loadPoster(url: String?){
    if(url != null){
        val posUrl = "https://image.tmdb.org/t/p/w185/$url"
        Glide.with(this.context).load(posUrl).into(this)
    }
}