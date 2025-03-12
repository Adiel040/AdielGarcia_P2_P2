package com.ucne.adielgarcia_p2_p2.presentation.depositos.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.adielgarcia_p2_p2.data.remote.Resource
import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import com.ucne.adielgarcia_p2_p2.data.repository.DepositosRepository
import com.ucne.adielgarcia_p2_p2.presentation.depositos.DepositoEvent
import com.ucne.adielgarcia_p2_p2.utils.formatAsDate
import com.ucne.adielgarcia_p2_p2.utils.formatAsString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val depositosRepository: DepositosRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: DepositoEvent) {
        when (event) {
            is DepositoEvent.LoadDeposito -> loadDeposito(event.deposito)

            is DepositoEvent.FechaChange -> fechaChange(event.fecha)
            is DepositoEvent.CuentaChange -> cuentaChange(event.idCuenta)
            is DepositoEvent.ConceptoChange -> conceptoChange(event.concepto)
            is DepositoEvent.MontoChange -> montoChange(event.monto)

            is DepositoEvent.SaveDeposito -> saveDeposito(event.navigateBack)
        }
    }

    private fun loadDeposito(deposito: String?) {
        if(deposito === null) return
        val result = Json.decodeFromString<DepositoDto>(deposito)
        _uiState.update { uiState ->
            uiState.copy(
                idDeposito = result.idDeposito,
                fecha = result.fecha.formatAsString(), //custom function
                idCuenta = result.idCuenta.toString(),
                concepto = result.concepto,
                monto = result.monto.toString()
            )
        }
    }

    private fun fechaChange(fecha: String) {
        _uiState.update { uiState ->
            uiState.copy(
                fecha = fecha
            )
        }
    }

    private fun cuentaChange(cuenta: String) {
        _uiState.update { uiState ->
            uiState.copy(
                idCuenta = cuenta
            )
        }
    }

    private fun conceptoChange(concepto: String) {
        _uiState.update { uiState ->
            uiState.copy(
                concepto = concepto
            )
        }
    }

    private fun montoChange(monto: String) {
        _uiState.update { uiState ->
            uiState.copy(
                monto = monto
            )
        }
    }

    private fun saveDeposito(navigateBack: () -> Unit) {
        if(!fieldsAreValid()) return

         val deposito = DepositoDto(
             idDeposito = _uiState.value.idDeposito,
             fecha = _uiState.value.fecha.formatAsDate() ?: Date(),
             idCuenta = _uiState.value.idCuenta.toInt(),
             concepto = _uiState.value.concepto,
             monto = _uiState.value.monto.toDouble()
         )

        navigateBack()

        viewModelScope.launch {
            when{
                // Update
                _uiState.value.idDeposito > 0 -> {
                    depositosRepository.updateDeposito(deposito).collect{ result ->
                        when (result) {
                            is Resource.Loading ->
                                _uiState.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }

                            is Resource.Success -> {
                                _uiState.update {
                                    it.copy(
                                        isLoading = false
                                    )
                                }
                            }

                            is Resource.Error ->
                                _uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = result.message ?: "Error al guardar el deposito"
                                    )
                                }
                        }
                    }
                }
                // Post
                else -> {
                    depositosRepository.createDeposito(deposito).collect{ result ->
                        when (result) {
                            is Resource.Loading ->
                                _uiState.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }

                            is Resource.Success -> {
                                _uiState.update {
                                    it.copy(
                                        isLoading = false
                                    )
                                }
                            }

                            is Resource.Error ->
                                _uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = result.message ?: "Error al guardar el deposito"
                                    )
                                }
                        }
                    }
                }
            }
        }
    }

    private fun fieldsAreValid(): Boolean {
        var stillValid = true

        if (_uiState.value.fecha.isBlank()) {
            _uiState.update { it.copy(errorFecha = "La fecha no puede estar vacía") }
            stillValid = false
        } else {
            _uiState.update { it.copy(errorFecha = null) }
        }
        val cuenta = _uiState.value.idCuenta.toIntOrNull() ?: 0
        if ( cuenta < 1) {
            _uiState.update { it.copy(errorCuenta = "Debe ingresar una cuenta") }
            stillValid = false
        } else {
            _uiState.update { it.copy(errorCuenta = null) }
        }

        if (_uiState.value.concepto.isBlank()) {
            _uiState.update { it.copy(errorConcepto = "El concepto no puede estar vacía") }
            stillValid = false
        } else {
            _uiState.update { it.copy(errorConcepto = null) }
        }
        val monto = _uiState.value.monto.toIntOrNull() ?: 0
        if (monto <= 0) {
            _uiState.update { it.copy(errorMonto = "El monto debe ser mayor a 0") }
            stillValid = false
        } else {
            _uiState.update { it.copy(errorMonto = null) }
        }

        return stillValid
    }
}