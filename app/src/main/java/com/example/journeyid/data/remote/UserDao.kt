package com.example.journeyid.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.journeyid.data.local.user.User

@Dao
 interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataEntity: List<User>)

    @Query("SELECT * FROM user_table")
    suspend fun getUserLocalData(): List<User>

    @Query("SELECT COUNT() FROM user_table WHERE email = :email")
    suspend fun checkedEmail(email : String): String
}