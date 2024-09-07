package org.mbds.unice.github.ui.users

import android.graphics.Color
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

}
*/

// nous avons utilise la librairie Glide pour telecharger les images

class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        // Charger l'image du profil avec Glide
        Glide.with(binding.root.context)
            .load(user.avatarUrl) // Suppose que avatarUrl est l'URL de l'avatar dans le modèle User
            .circleCrop() // Appliquer un crop circulaire pour un avatar
            .into(binding.itemListUserAvatar)

        // Assigner le nom d'utilisateur
        binding.itemListUserUsername.text = user.login

        // Changer l'icône du bouton selon le statut activé/désactivé
        binding.itemListUserDeleteButton.setImageResource(
            if (user.isActive) R.drawable.ic_delete_black_24dp else R.drawable.ic_restore_black_24dp
        )

        // Changer le fond de la cellule selon le statut activé/désactivé
        binding.root.setBackgroundColor(
            if (user.isActive) Color.WHITE else Color.RED
        )

        // Assigner l'action de basculement d'état activé/désactivé
        binding.itemListUserDeleteButton.setOnClickListener {
            callback.onClickDelete(user)
        }
    }
}





