package org.d3if2009.pyramidvolume.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Insert
    fun insert(bmi: DataEntity)

    @Query("SELECT * FROM data ORDER BY id DESC")
    fun getLastHasil(): LiveData<List<DataEntity>>

    @Query("DELETE FROM data")
    fun clearData()
}