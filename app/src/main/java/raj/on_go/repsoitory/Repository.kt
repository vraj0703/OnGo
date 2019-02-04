package raj.on_go.repsoitory

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import raj.on_go.src.database.Dao
import raj.on_go.src.database.Word


class Repository(private val dao: Dao) {

    val words: LiveData<List<Word>> = dao.getAllWords()

    @WorkerThread
    fun insert(word: Word) {
        dao.insert(word)
    }

    @WorkerThread
    fun find(word: String): Word? {
        return dao.word(word)
    }
}