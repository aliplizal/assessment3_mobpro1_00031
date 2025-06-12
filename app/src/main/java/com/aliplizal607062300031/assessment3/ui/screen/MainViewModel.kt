package com.aliplizal607062300031.assessment3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliplizal607062300031.assessment3.model.Buku
import com.aliplizal607062300031.assessment3.network.ApiStatus
import com.aliplizal607062300031.assessment3.network.BukuApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Buku>())
            private set

    var status = MutableStateFlow(ApiStatus.LOADING)
            private set

    init {
        retrieveData()
    }

    private fun retrieveData() {
        Log.d("MainViewModel", "retrieveData() called")
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = BukuApi.service.getBuku()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}