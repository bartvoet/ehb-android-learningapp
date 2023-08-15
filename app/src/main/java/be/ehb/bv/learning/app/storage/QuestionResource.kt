package be.ehb.bv.learning.app.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "questionresources")
data class QuestionResource(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "dateUpdated")
    val dateUpdated: Date,
    @ColumnInfo(name = "url")
    val url: String
)