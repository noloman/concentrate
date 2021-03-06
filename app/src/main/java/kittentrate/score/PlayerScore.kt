package kittentrate.score

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Manuel Lorenzo on 23/03/2017.
 */
@Entity(tableName = "Score")
data class PlayerScore(@field:ColumnInfo(name = "name") var playerName: String?,
                       @field:ColumnInfo(name = "score") var playerScore: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    lateinit var id: Integer
}
