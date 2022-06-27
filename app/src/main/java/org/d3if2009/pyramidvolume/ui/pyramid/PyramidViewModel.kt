package org.d3if2009.pyramidvolume.ui.pyramid

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2009.pyramidvolume.MainActivity
import org.d3if2009.pyramidvolume.model.Pyramid
import org.d3if2009.pyramidvolume.network.ApiStatus
import org.d3if2009.pyramidvolume.network.PyramidApi
import org.d3if2009.pyramidvolume.network.UpdateWorker
import java.util.concurrent.TimeUnit

class PyramidViewModel: ViewModel() {
    private val data = MutableLiveData<List<Pyramid>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(PyramidApi.service.getResult())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("PyramidViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<Pyramid>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}