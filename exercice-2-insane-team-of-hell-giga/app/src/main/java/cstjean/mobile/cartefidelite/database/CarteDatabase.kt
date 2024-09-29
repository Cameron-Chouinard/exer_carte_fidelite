package cstjean.mobile.cartefidelite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cstjean.mobile.cartefidelite.carte.Carte
import androidx.room.AutoMigration
/*
@Database(entities = [Carte::class],
    version = 1,
    exportSchema = true)
abstract class TravailDatabase : RoomDatabase() {
    abstract fun carteDao(): CarteDao
}*/

@Database(entities = [Carte::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration (from = 2, to = 3)
    ])
// @TypeConverters(CarteTypeConverters::class)
abstract class CarteDatabase : RoomDatabase() {
    abstract fun carteDao(): CarteDao
}