package com.ucne.adielgarcia_p2_p2.presentation.depositos.list

import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto

data class ListUiState (
    val depositos: List<DepositoDto> = emptyList(),

    val isLoading: Boolean = false,
    val errorMessage: String? = null
)