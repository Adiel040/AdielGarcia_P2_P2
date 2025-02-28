package com.ucne.adielgarcia_p2_p2.presentation.depositos.form

data class FormUiState(
    val idDeposito: Int = 0,

    val fecha: String = "",
    val errorFecha: String? = null,

    val idCuenta: String = "",
    val errorCuenta: String? = null,

    val concepto: String = "",
    val errorConcepto: String? = null,

    val monto: String = "",
    val errorMonto : String? = null,

    //Llamado de la API
    val isLoading : Boolean = false,
    val errorMessage: String? = null
)
