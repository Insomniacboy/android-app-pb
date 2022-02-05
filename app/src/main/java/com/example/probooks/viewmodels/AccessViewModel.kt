package com.example.probooks.viewmodels

import android.content.Context
import androidx.lifecycle.*
import com.example.probooks.models.AccessDetail
import com.example.probooks.models.AccessItem
import com.example.probooks.repository.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AccessViewModel : ViewModel() {

    var accessitems: MutableLiveData<MutableList<AccessItem>> = MutableLiveData()
    val item = MutableLiveData<AccessDetail>()
    fun init (context : Context) {
        if(accessitems.value != null)
            return
    }

    private val repo = Repo()

    fun fetchAccessData(): MutableLiveData<MutableList<AccessItem>> {
        viewModelScope.launch(IO) {
            accessitems.postValue(repo.getAccessEventsList())
        }
        return accessitems
    }

    fun fetchAccessEvent(url: String): MutableLiveData<AccessDetail> {
        viewModelScope.launch(IO) {
            item.postValue(repo.getAccessEvent(url))
        }
        return item
    }
}