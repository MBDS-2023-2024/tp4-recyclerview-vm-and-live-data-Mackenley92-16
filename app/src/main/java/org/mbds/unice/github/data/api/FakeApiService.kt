package org.mbds.unice.github.data.api

import android.util.Log
import org.mbds.unice.github.data.model.User

class FakeApiService : ApiService {
    private val _users = mutableListOf<User>()
    init {
        _users.addAll(FakeApiServiceGenerator.FAKE_USERS)
    }


    /**
     * Return a list of [User]
     * Those users must be generated by [FakeApiServiceGenerator]
     */
    override fun getUsers(): List<User> {
        return _users
    }

    /**
     * Generate a random [User] and add it [FakeApiService.users] list.
     * This user must be get from the [FakeApiServiceGenerator.FAKE_USERS_RANDOM] list.
     */

    //nous avons ajoute un log
    override fun addRandomUser() {
        val randomUser = FakeApiServiceGenerator.FAKE_USERS_RANDOM.random()
        _users.add(randomUser)
        Log.d("FakeApiService", "Random user added: $randomUser")
    }

    /**
     * Delete a [User] from the [FakeApiService.users] list.
     */
    override fun deleteUser(username: User) {
        val index = _users.indexOf(username)
        if (index != -1) {
            _users[index] = username.copy(isActive = !username.isActive) // Bascule l'état
        }
    }
}