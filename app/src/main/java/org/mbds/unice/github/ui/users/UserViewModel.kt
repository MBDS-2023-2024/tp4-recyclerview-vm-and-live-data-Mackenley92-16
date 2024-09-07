package org.mbds.unice.github.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.data.repository.UserRepository
import org.mbds.unice.github.di.Injection


class UserViewModel : ViewModel() {

    // On a besoin d'instancier le repository une fois (cf. Singleton)
    private val userRepository: UserRepository = Injection.getRepository()

    // On utilise un MutableLiveData pour pouvoir modifier la liste des utilisateurs dans le ViewModel
    // On expose un LiveData (qui n'autorise pas les mpodifications) pour que l'activité puisse observer les changements
    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        // On initialise la liste des utilisateurs à la création du ViewModel
        refresh()
    }
// nous avons ajoute des logs pour eviter a l'appli de faire un refresh avant de prendre en compte les nouvelles modifications
    private fun refresh() {
        _users.value = userRepository.getUsers()
        Log.d("UserViewModel", "User list refreshed: ${_users.value}")
    }

    fun generateRandomUser() {
        userRepository.addRandomUser()
        Log.d("UserViewModel", "Random user generated")
        refresh()
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
        Log.d("UserViewModel", "User ${user.login} deleted")
        refresh()
    }
}