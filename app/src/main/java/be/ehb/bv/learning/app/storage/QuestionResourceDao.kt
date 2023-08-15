package be.ehb.bv.learning.app.storage

import androidx.room.*

@Dao
interface QuestionResourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestionResource(resource: QuestionResource)

    @Query("SELECT * FROM questionresources ORDER BY dateUpdated DESC")
    fun getQuestionResources(): List<QuestionResource>

    @Update
    fun updateQuestionResource(note: QuestionResource)

    @Delete
    fun deleteQuestionResource(note: QuestionResource)

}