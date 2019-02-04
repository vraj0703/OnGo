package raj.on_go.src.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = Constants.wordTable)
data class Word(@PrimaryKey
                @NonNull
                @ColumnInfo(name = Constants.wordColumn)
                val word: String, val meaning: String)