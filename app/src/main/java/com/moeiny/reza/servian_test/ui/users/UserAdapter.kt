package com.moeiny.reza.servian_test.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.servian_test.data.model.uimodel.ShowUserModel
import com.moeiny.reza.servian_test.databinding.UserBinding

class UserAdapter(private val onCardClicked: (id: String) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var listUser: List<ShowUserModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userBinding = UserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(userBinding)
    }

    override fun getItemCount(): Int {
        return listUser.count()
    }

    override fun onBindViewHolder(holderUser: UserViewHolder, position: Int) {
        var info = listUser.get(position)
        holderUser.userBinding.cardPhotorowParent.setOnClickListener {
            onCardClicked(info.id.toString())
        }
        holderUser.bind(info)
    }

    inner class UserViewHolder(val userBinding: UserBinding) :
        RecyclerView.ViewHolder(userBinding.root) {
        fun bind(modelViewShowUser: ShowUserModel) {
            this.userBinding.usershow = modelViewShowUser
        }
    }
}