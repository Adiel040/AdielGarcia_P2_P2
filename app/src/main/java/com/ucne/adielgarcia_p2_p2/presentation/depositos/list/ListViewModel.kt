package com.ucne.adielgarcia_p2_p2.presentation.depositos.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.adielgarcia_p2_p2.data.remote.Resource
import com.ucne.adielgarcia_p2_p2.data.repository.DepositosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: DepositosRepository
):ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getDepositos()
    }

    fun getDepositos() {

        viewModelScope.launch {
            repository.getDepositos().collect{ result ->
                when(result){
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            depositos = result.data?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            }
        }
    }
}