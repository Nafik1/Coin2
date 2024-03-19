package com.example.myapplication.Data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Data.api.model.CoinInfoDto

@Database(entities = [CoinInfoDBModel::class], version = 4, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    companion object{
        private var db : DataBase? = null
        private const val db_name = "main.db"
        private val LOCK = Any()


        fun getInstance(context : Context) : DataBase {
            kotlin.synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, DataBase::class.java, db_name)
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }

        }
    }
    abstract fun coinPriceInfoDao() : CoinPriceInfoDao
}