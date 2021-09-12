package com.moeiny.reza.servian_test.ui.photoes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.servian_test.data.model.uimodel.ShowPhotoModel
import com.moeiny.reza.servian_test.databinding.PhotoBinding
import com.squareup.picasso.Picasso


class PhotoAdapter(private val onCardClicked: (photo: ShowPhotoModel) -> Unit):
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>()  {

    var listPhoto: List<ShowPhotoModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val photoBinding = PhotoBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(photoBinding)
    }

    override fun getItemCount(): Int {
        return listPhoto.count()
    }

    override fun onBindViewHolder(holderPhoto: PhotoViewHolder, position: Int) {
        var photo=listPhoto.get(position)
        holderPhoto.photoBinding.cardPhotorowParent.setOnClickListener {
            onCardClicked(photo)
        }
        holderPhoto.bind(photo)
    }

    inner class PhotoViewHolder(val photoBinding: PhotoBinding): RecyclerView.ViewHolder(photoBinding.root){
      fun bind(photoModelView: ShowPhotoModel) {
          this.photoBinding.photoshow = photoModelView
      }
    }

    companion object{
         @JvmStatic
         @BindingAdapter("imageUrl")
         fun loadImage(view: ImageView,url:String) {
            Picasso.get().load(url).into(view)
         }
    }
}