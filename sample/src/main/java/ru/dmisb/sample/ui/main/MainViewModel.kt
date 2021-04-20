package ru.dmisb.sample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmisb.sample.data.remote.NetworkManager
import ru.dmisb.sample_api_client.res.GeneralInfoData

class MainViewModel : ViewModel() {

    private val _generalInfo = MutableLiveData<GeneralInfoData?>()
    val generalInfo: LiveData<GeneralInfoData?> = _generalInfo

    init {
        viewModelScope.launch {
            val res = NetworkManager.getGeneralInfo()
            _generalInfo.postValue(res)
        }
    }
}