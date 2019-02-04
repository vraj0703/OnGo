package raj.on_go.src.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface Dao {

    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM " + Constants.wordTable)
    fun deleteAll()

    @Query("Select * from " + Constants.wordTable)
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM word_table WHERE _word = :word LIMIT 1")
    fun word(word: String): Word?
}