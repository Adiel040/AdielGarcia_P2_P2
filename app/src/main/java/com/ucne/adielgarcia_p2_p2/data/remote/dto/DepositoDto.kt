package com.ucne.adielgarcia_p2_p2.data.remote.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class DepositoDto (
    val idDeposito: Int,
    @Contextual val fecha: Date,
    val idCuenta: Int,
    val concepto: String,
    val monto: Int
)
