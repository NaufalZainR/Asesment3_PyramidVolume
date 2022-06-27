package org.d3if2009.pyramidvolume.model

import org.d3if2009.pyramidvolume.db.DataEntity

fun DataEntity.convert(): HasilHitung {
    val hasil = (inputPanjang * inputLebar * inputTinggi) / 3

    return HasilHitung(inputPanjang, inputLebar, inputTinggi, hasil)
}