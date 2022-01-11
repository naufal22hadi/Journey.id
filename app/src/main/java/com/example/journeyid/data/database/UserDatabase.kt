package com.example.journeyid.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.journeyid.data.local.user.User
import com.example.journeyid.data.remote.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).build()
            }
            return INSTANCE!!
        }
    }
}