package com.ucne.adielgarcia_p2_p2.presentation.navigation

import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data class Depositos(val deposito: DepositoDto? = null) : Screen()

    @Serializable
    data object DepositosList : Screen()
}