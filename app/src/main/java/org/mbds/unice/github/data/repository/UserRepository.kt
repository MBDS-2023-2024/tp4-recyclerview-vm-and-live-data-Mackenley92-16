package org.mbds.unice.github.data.repository

import org.mbds.unice.github.data.api.ApiService
import org.mbds.unice.github.data.model.User

class UserRepository(
    private val apiService: ApiService
) {
    // Stocke les utilisateurs dans une liste mutable
    private val users = mutableListOf<User>()

    fun getUsers(): List<User> {
        return apiService.getUsers() // Utilise l'API pour récupérer la liste des utilisateurs
        }


    fun addRandomUser() {
        apiService.addRandomUser() // Utilise l'API pour ajouter un utilisateur aléatoire
    }

    fun deleteUser(user: User) {
        apiService.deleteUser(user) // Utilise l'API pour supprimer un utilisateur
    }
}