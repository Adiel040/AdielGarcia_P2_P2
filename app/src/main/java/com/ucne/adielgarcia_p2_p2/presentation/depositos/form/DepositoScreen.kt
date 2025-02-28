package com.ucne.adielgarcia_p2_p2.presentation.depositos.form

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.adielgarcia_p2_p2.presentation.depositos.DepositoEvent
import com.ucne.adielgarcia_p2_p2.ui.components.AppTextField
import com.ucne.adielgarcia_p2_p2.ui.components.ShowDatePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositoScreen(
    deposito: String? = null,
    viewModel: FormViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    val onEvent = viewModel::onEvent
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val showDatePicker = remember { mutableStateOf(false) }

    LaunchedEffect(deposito) {
        Log.d("RESULT","WAAY" + deposito)
        onEvent(DepositoEvent.LoadDeposito(deposito))
    }

    if (showDatePicker.value) {
        ShowDatePickerDialog(
            context = context,
            onDateSelected = { selectedDate ->
                onEvent(DepositoEvent.FechaChange(selectedDate))
                showDatePicker.value = false
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Formulario de deposito",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Back"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                focusManager.clearFocus()
                onEvent(DepositoEvent.SaveDeposito(onBack))
            }) {
                Icon(
                    imageVector = if(deposito != null) Icons.Default.Edit else Icons.Default.Save,
                    contentDescription = "Guardar"
                )
            }
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                value = uiState.fecha,
                onValueChange = {},
                label = "Fecha",
                trailingIcon = {
                    IconButton(onClick = { showDatePicker.value = true }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Seleccionar Fecha"
                        )
                    }
                },
                error = uiState.errorFecha,
                readOnly = true
            )
            AppTextField(
                value = uiState.idCuenta,
                onValueChange = { onEvent(DepositoEvent.CuentaChange(it)) },
                label = "Número de Cuenta",
                error = uiState.errorCuenta,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            AppTextField(
                value = uiState.concepto,
                onValueChange = { onEvent(DepositoEvent.ConceptoChange(it)) },
                label = "Concepto",
                error = uiState.errorConcepto
            )
            AppTextField(
                value = uiState.monto,
                onValueChange = { onEvent(DepositoEvent.MontoChange(it)) },
                label = "Monto",
                error = uiState.errorMonto,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}