package org.mbds.unice.github.data.repository

import org.mbds.unice.github.data.api.ApiService
import org.mbds.unice.github.data.model.User

class UserRepository(
    private val apiService: ApiService
) {
    // Stocke les utilisateurs dans une liste mutable
    private val users = mutableListOf<User>()

    fun getUsers(): List<User> {
        return apiService.getUsers()
        }


    fun addRandomUser() {
        apiService.addRandomUser()
    }

    fun deleteUser(user: User) {
        apiService.deleteUser(user)
    }
}