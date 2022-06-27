package org.d3if2009.pyramidvolume.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2009.pyramidvolume.db.DataDao
import org.d3if2009.pyramidvolume.db.DataEntity
import org.d3if2009.pyramidvolume.model.HasilHitung

class HitungViewModel(private val db: DataDao): ViewModel() {

    private val hasilHitung = MutableLiveData<HasilHitung?>()

    fun calculate(inputPanjang: Float, inputLebar: Float, inputTinggi: Float) {
        val hasil = (inputPanjang * inputLebar * inputTinggi) / 3
        hasilHitung.value = HasilHitung(inputPanjang, inputLebar, inputTinggi, hasil)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHasil = DataEntity(
                    inputPanjang = inputPanjang,
                    inputLebar = inputLebar,
                    inputTinggi = inputTinggi,
                    hasil = hasil
                )
                db.insert(dataHasil)
            }
        }
    }
    fun getHasil(): LiveData<HasilHitung?> = hasilHitung
}