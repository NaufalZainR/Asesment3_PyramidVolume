package org.d3if2009.pyramidvolume.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var inputPanjang: Float,
    var inputLebar: Float,
    var inputTinggi: Float,
    var hasil : Float
)