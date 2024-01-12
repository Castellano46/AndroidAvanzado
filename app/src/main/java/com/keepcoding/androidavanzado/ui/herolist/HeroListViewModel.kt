package com.keepcoding.androidavanzado.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.domain.model.HeroUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _heros = MutableLiveData<HeroListState>()
    val heros: LiveData<HeroListState> get() = _heros

    fun getHeroList() {
        viewModelScope.launch {
            _heros.value = HeroListState.Loading
            val heros = withContext(dispatcher) {
                repository.getHeroList()
            }
            _heros.value = HeroListState.Success(heros)
        }
    }
}

sealed class HeroListState{
    data object Loading: HeroListState()
    data class Success(val heros: List<HeroUI>): HeroListState()
    data object Error: HeroListState()
    data object Idle: HeroListState()

}
