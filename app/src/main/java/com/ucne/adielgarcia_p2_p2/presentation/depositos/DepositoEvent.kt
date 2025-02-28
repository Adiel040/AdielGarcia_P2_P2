package com.ucne.adielgarcia_p2_p2.presentation.depositos

import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto

sealed interface DepositoEvent {
    //Load data
    data class LoadDeposito(val deposito: DepositoDto?): DepositoEvent

    //OnChanges
    data class FechaChange(val fecha: String): DepositoEvent
    data class CuentaChange(val idCuenta: String): DepositoEvent
    data class ConceptoChange(val concepto: String) : DepositoEvent
    data class MontoChange(val monto: String) : DepositoEvent

    //OnSave
    data class SaveDeposito(val navigateBack: () -> Unit) : DepositoEvent
}