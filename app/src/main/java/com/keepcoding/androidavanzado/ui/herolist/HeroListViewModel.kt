package com.keepcoding.androidavanzado.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.domain.model.Hero
import kotlinx.coroutines.launch

class HeroListViewModel : ViewModel() {

    private val repository = Repository()

    private val _heros = MutableLiveData<List<Hero>>()
    val heros: LiveData<List<Hero>> get() =  _heros

    fun getHeroList() {
        viewModelScope.launch {
            val heros = repository.getHeroList()
            _heros.value = heros
        }
    }
}
