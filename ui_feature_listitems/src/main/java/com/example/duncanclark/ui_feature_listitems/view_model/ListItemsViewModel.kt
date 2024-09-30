package com.example.duncanclark.ui_feature_listitems.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ListItems
import com.example.duncanclark.domain.usecase.GetListItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemsViewModel @Inject constructor(
    private val useCase: GetListItemsUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    companion object {
        const val SAVED_STATE_HANDLE_KEY = "list_items_view_model_ui_state"
    }

    private val _uiState = MutableStateFlow<UiState<ListItems>>(UiState.Idle)
    val uiState: StateFlow<UiState<ListItems>> = _uiState

    private fun loading() {
        _uiState.value = UiState.Loading
    }

    init {
        val savedState = savedStateHandle.get<UiState<ListItems>>(SAVED_STATE_HANDLE_KEY)
        savedState?.let { _uiState.value = it }
        getListItems()
    }

    private fun getListItems() {
        viewModelScope.launch {
            loading()
            useCase.execute().collect { result ->
                _uiState.value = when {
                    result.isSuccess -> {
                        UiState.Success(result.getOrDefault(emptyList()))
                    }
                    result.isFailure -> {
                        UiState.Error("Oh no! Something went wrong!")
                    }
                    else -> UiState.Error("Oh no! Something went REALLY wrong!")
                }
            }
        }
    }
}