package com.example.probooks.viewmodels

import android.content.ClipData
import android.content.Context
import androidx.lifecycle.*
import com.example.probooks.MainActivity
import com.example.probooks.models.AccessItem
import com.example.probooks.models.EventDetail
import com.example.probooks.models.EventItem
import com.example.probooks.repository.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {

    var items: MutableLiveData<MutableList<EventItem>> = MutableLiveData()
    val item = MutableLiveData<EventDetail>()
    fun init (context : Context) {
        if(items.value != null)
            return
    }


    private val repo = Repo()
    fun fetchData(): MutableLiveData<MutableList<EventItem>> {
        viewModelScope.launch(IO) {
            items.postValue(repo.getEventsList())
        }
        return items
    }

    fun fetchEvent(url: String): MutableLiveData<EventDetail> {
        viewModelScope.launch(IO) {
            item.postValue(repo.getEvent(url))
        }
        return item
    }


}