package org.mbds.unice.github.ui.users

import android.view.View
import android.widget.ImageButton
import com.bumptech.glide.Glide
import android.widget.ImageView
import org.mbds.unice.github.databinding.ItemListUserBinding
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User

/**TODO : Use viewBinding instead of findviewbyid
class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var avatar: ImageView = itemView.findViewById(R.id.item_list_user_avatar)
    private val username: TextView = itemView.findViewById(R.id.item_list_user_username)
    private val deleteButton: ImageButton = itemView.findViewById(R.id.item_list_user_delete_button)

    fun bind(user: User, callback: UserListAdapter.Listener) {
        // TODO : Utiliser une librairie pour charger l'image (glide, coil, )
        username.text = user.login
        deleteButton.setOnClickListener { callback.onClickDelete(user) }
    }

}*/
class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        // Charger l'image du profil avec Glide
        Glide.with(binding.root.context)
            .load(user.avatarUrl) // Suppose que avatarUrl est l'URL de l'avatar dans le modèle User
            .circleCrop() // Appliquer un crop circulaire pour un avatar
            .into(binding.itemListUserAvatar)

        // Assigner le nom d'utilisateur
        binding.itemListUserUsername.text = user.login

        // Assigner l'action de suppression
        binding.itemListUserDeleteButton.setOnClickListener { callback.onClickDelete(user) }
    }
}

