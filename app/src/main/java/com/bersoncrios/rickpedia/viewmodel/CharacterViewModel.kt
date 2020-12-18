package com.bersoncrios.rickpedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersoncrios.rickpedia.model.Chars
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.network.ConfigureRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CharacterViewModel: ViewModel() {
    private val service = ConfigureRetrofit()
    private val _items = MutableLiveData<Chars>()
    private val _details = MutableLiveData<Result>()

    val items: LiveData<Chars>
        get() = _items

    val details: LiveData<Result>
        get() = _details

    fun fetchChars() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getChars()
            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _items.value = res.body()
                }
            }
        }
    }

    fun fetchDetailsOfChars(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getCharsDetail(id)
            if (res.isSuccessful){
                withContext(Dispatchers.Main){
                    _details.value = res.body()
                }
            }
        }
    }
}