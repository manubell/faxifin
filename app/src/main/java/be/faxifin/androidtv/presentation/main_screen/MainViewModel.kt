package be.faxifin.androidtv.presentation.main_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.faxifin.androidtv.common.Resource
import be.faxifin.androidtv.data.jellyfin.model.JellyfinLibraryDto
import be.faxifin.androidtv.domain.jellyfin.use_case.JellyfinUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(jellyfinUsecases: JellyfinUsecases) : ViewModel() {
    private val _mainState = MutableLiveData<Resource<List<JellyfinLibraryDto>>>()
    val libraryState: LiveData<Resource<List<JellyfinLibraryDto>>> = _mainState

    init {
        jellyfinUsecases.getLibraries()
            .onEach { result ->
                delay(500L)
                run {
                    Log.i("testing", "viewmodel result: $result")
                    _mainState.postValue(result)
                }
            }.launchIn(viewModelScope)
    }
}