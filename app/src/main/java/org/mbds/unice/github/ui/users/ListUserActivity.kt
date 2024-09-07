package org.mbds.unice.github.ui.users

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ActivityListUserBinding

/**class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    // TODO : Utiliser viewBinding
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    // By lazy permet de faire du chargement parresseux,
    // L'adapteur sera crée au premier appel
    private val adapter: UserListAdapter by lazy {
        UserListAdapter(this)
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
        configureFab()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.users.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun configureRecyclerView() {
        recyclerView = findViewById(R.id.activity_list_user_rv)
        recyclerView.adapter = adapter
    }

    private fun configureFab() {
        fab = findViewById(R.id.activity_list_user_fab)
        fab.setOnClickListener {
            TODO("Ajouter un utilisateur aléatoire")
        }
    }

    override fun onClickDelete(user: User) {
        TODO("Ajouter des logs pour tracer les actions de l'utilisateur")
        TODO("Ajouter une boite de dialogue pour confirmer la suppression et supprimer l'utilisateur si l'utilisateur confirme")
    }
}*/

//nous utilisons ViewBiding

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {

    // Utilisation de ViewBinding
    private lateinit var binding: ActivityListUserBinding

    // By lazy permet de faire du chargement parresseux,
    // L'adapteur sera créé au premier appel
    private val adapter: UserListAdapter by lazy {
        UserListAdapter(this)
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Utiliser ViewBinding pour accéder aux vues
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureFab()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        // Observer les changements dans la liste des utilisateurs
        viewModel.users.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun configureRecyclerView() {
        // Configuration du RecyclerView avec ViewBinding
        binding.activityListUserRv.adapter = adapter
    }

    private fun configureFab() {
        // Lorsque le bouton est cliqué, ajouter un utilisateur aléatoire via ViewModel
        binding.activityListUserFab.setOnClickListener {
            viewModel.generateRandomUser()
        }
    }

    override fun onClickDelete(user: User) {
        // Ajouter des logs pour tracer les actions de l'utilisateur
        Log.d("ListUserActivity", "Suppression de l'utilisateur: ${user.login}")

        // Ajouter une boîte de dialogue pour confirmer la suppression
        AlertDialog.Builder(this)
            .setTitle("changer le statut de l'utilisateur")
            .setMessage("Êtes-vous sûr de changer le statut de l'utilisateur ${user.login} ?")
            .setPositiveButton("Oui") { _, _ ->
                // Si l'utilisateur confirme, supprimer l'utilisateur via le ViewModel
                viewModel.deleteUser(user)
                Log.d("ListUserActivity", "Utilisateur supprimé: ${user.login}")
            }
            .setNegativeButton("Non", null)
            .show()
    }
}