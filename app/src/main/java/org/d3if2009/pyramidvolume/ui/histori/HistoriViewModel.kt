package org.d3if2009.pyramidvolume.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2009.pyramidvolume.db.DataDao

class HistoriViewModel(private val db: DataDao) : ViewModel() {
    val data = db.getLastHasil()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}