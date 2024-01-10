package com.keepcoding.androidavanzado.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.domain.model.HeroUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _heros = MutableLiveData<List<HeroUI>>()
    val heros: LiveData<List<HeroUI>> get() = _heros

    fun getHeroList() {
        viewModelScope.launch {
            val heros = withContext(Dispatchers.IO) {
                repository.getHeroList()
            }
            _heros.value = heros
        }
    }
}
