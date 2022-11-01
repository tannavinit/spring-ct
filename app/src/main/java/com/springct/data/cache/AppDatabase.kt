package com.springct.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.springct.data.cache.dao.EmployeeCache
import com.springct.data.cache.dao.EmployeeDao

@Database(
    entities = [EmployeeCache::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
}