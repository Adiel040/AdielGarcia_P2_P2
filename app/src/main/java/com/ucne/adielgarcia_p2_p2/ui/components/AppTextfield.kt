package com.ucne.adielgarcia_p2_p2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    error: String? = null,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            trailingIcon = trailingIcon,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            modifier = Modifier.fillMaxWidth()
        )
        error?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}