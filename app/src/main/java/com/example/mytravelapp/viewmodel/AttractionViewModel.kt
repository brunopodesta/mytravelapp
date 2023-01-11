package com.example.mytravelapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytravelapp.data.Attraction
import com.example.mytravelapp.repositorie.AttractionRepository
import kotlinx.coroutines.launch

class AttractionViewModel : ViewModel() {

    private val repository = AttractionRepository()

    private val _attractionListLiveData = MutableLiveData<List<Attraction>>()
    val attractionListLiveData : LiveData<List<Attraction>> get() = _attractionListLiveData

    //AttractionDetailFragment
    private val _selectedAttractionLiveData = MutableLiveData<Attraction>()
    val selectedAttractionLiveData : LiveData<Attraction> get() = _selectedAttractionLiveData

    private val _locationSelected = MutableLiveData<Attraction>()
    val locationSelected : LiveData<Attraction> get() = _locationSelected


    fun init(context: Context) {
        viewModelScope.launch {
            val attractions = repository.uploadAttractions(context)
            _attractionListLiveData.postValue(attractions)
        }
    }

    fun onAttractionSelected(attractionId : String) {
        val attraction = _attractionListLiveData.value?.find { it.id == attractionId } ?: return
        _selectedAttractionLiveData.value = attraction
    }

    fun onLocationSelected(attraction : Attraction) {
        _locationSelected.postValue(attraction)
    }

}