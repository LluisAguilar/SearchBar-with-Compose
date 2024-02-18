package com.cvs.code.challenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvs.code.challenge.domain.Response
import com.cvs.code.challenge.domain.usecase.GetImageSearchUseCase
import com.cvs.code.challenge.ui.state.ImageSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImageSearchUseCase: GetImageSearchUseCase
) : ViewModel() {

    private val _uiState : MutableStateFlow<ImageSearchState> =
        MutableStateFlow(ImageSearchState.Loading)

    val uiState: StateFlow<ImageSearchState> get() = _uiState

    init {
        getImageSearch("Cars")
    }

    fun getImageSearch(tags:String) {
        viewModelScope.launch {
            getImageSearchUseCase(tags)
                .collect { response ->
                    when(response){

                        is Response.Loading -> {
                            _uiState.value = ImageSearchState.Loading
                        }

                        is Response.Success -> {
                            _uiState.value = ImageSearchState.SuccessState(response.data)
                        }

                        is Response.Error -> {
                            _uiState.value = ImageSearchState.ErrorState(response.exception.message?:"Unknown error")
                        }
                    }
                }
        }
    }
}