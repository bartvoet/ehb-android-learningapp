package be.ehb.bv.learning.app.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [QuestionResource::class],
    version = 2,
    exportSchema = true,
)
@TypeConverters(DateConverters::class)
abstract class QuestionResourceDatabase : RoomDatabase() {

    abstract fun questionResourceDao(): QuestionResourceDao

    companion object {

        @Volatile
        private var INSTANCE: QuestionResourceDatabase? = null

        fun getDatabase(context: Context): QuestionResourceDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): QuestionResourceDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuestionResourceDatabase::class.java,
                "notes_database"
            )
                .build()
        }
    }
}