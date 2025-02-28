package com.ucne.adielgarcia_p2_p2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import com.ucne.adielgarcia_p2_p2.utils.formatAsString

@Composable
fun DepositoRow(deposito: DepositoDto, onClick: (DepositoDto) -> Unit) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        onClick = { onClick(deposito) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Cuenta ${deposito.idCuenta}")
            Text(deposito.fecha.formatAsString())
        }
        Text("Monto ${deposito.monto}")
        Text(deposito.concepto)
    }
}