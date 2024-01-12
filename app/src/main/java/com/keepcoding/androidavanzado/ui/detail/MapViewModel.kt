package com.keepcoding.androidavanzado.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.domain.model.HeroDetailUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _hero = MutableLiveData<HeroDetailUI>()
    val hero: LiveData<HeroDetailUI> get() = _hero

    fun getHeroDetail(name: String){
        viewModelScope.launch {
            val hero = withContext(Dispatchers.IO){
                repository.getHeroDetail(name)
            }

            _hero.value = hero
        }
    }
}
