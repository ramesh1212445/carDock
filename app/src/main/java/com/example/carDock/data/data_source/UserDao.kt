package com.example.carDock.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query(
        "SELECT name , email , contact_number , address FROM user"
    )
    fun getUsers(): Flow<List<BaseUser>>?

    @Query(
        "SELECT name , email , contact_number , address FROM user WHERE id = :id"
    )
    fun getUserById(id: Long): BaseUser?

    @Insert(onConflict = OnConflictStrategy.ABORT, entity = User::class)
    suspend fun addUser(user: User)

    @Query(
        "DELETE FROM user WHERE id = :id"
    )
    suspend fun delUser(id: Long)

    @Query(
        "SELECT balance FROM user WHERE id = :id"
    )
    fun getUserBalance(id: Long): Long?

    @Query(
        "SELECT name , email , contact_number , address FROM user WHERE email = :email AND password = :psw"
    )
    fun getAuthUser(email: String, psw: String): BaseUser?


}