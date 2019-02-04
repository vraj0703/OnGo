package raj.on_go.src.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Word::class], version = Constants.dbVersion)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: Dao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun db(context: Context): AppDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Constants.dbName
                ).build()
                this.instance = instance
                return instance
            }
        }
    }
}