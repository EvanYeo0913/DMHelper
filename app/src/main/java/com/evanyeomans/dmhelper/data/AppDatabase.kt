package com.evanyeomans.dmhelper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evanyeomans.dmhelper.models.ItemEntity

// This file defines the database.
// The @Database annotation is part of the Room library.
// You will see an error on the annotation if you have not added the Room
// library to the build.gradle file.

// Room features. Room is a library that makes it easier to work with SQLite databases.
// It extends RoomDatabase, which is a class that is part of the Room library.
// The jokeDao is another class that contains the functions to access the database.
// When the app is run, the database is created if it does not exist.
// If the database exists, then the existing database is used.

// Notice the singleton pattern.
// It is a singleton, meaning there is only one instance of the class.
// The instance is created the first time the getInstance function is called.
// The instance is stored in the INSTANCE variable.
// The instance is returned when the getInstance function is called again.
// The database is defined in the @Database annotation.
// The properties of the database are defined in the entities and version.
// The entities are the tables in the database such as JokeEntity.
// The version is the version of the database. If you change the database, you must increment the version.
// The exportSchema is set to false to prevent a warning message.
// The database is automatically created in the directory /data/data/com.shadsluiter.jokesapp/databases/joke_database.
// Use the device file explorer in Android Studio to view the database.
// The device file explorer is located in the bottom right tab of Android Studio.

@Database(
    entities = [ItemEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "room_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
