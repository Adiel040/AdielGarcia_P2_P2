package com.ucne.adielgarcia_p2_p2.presentation.depositos.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import com.ucne.adielgarcia_p2_p2.ui.components.DepositoRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositosListScreen(
    onDepositoClick: (DepositoDto) -> Unit,
    onNewClick: () -> Unit,
    viewModel: ListViewModel = hiltViewModel(),
) {
    remember{
        viewModel.getDepositos()
        0
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Depositos",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNewClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Guardar"
                )
            }
        }

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(items = uiState.depositos) { deposito ->
                DepositoRow(deposito) { clickeado ->
                    onDepositoClick(clickeado)
                }
            }
        }
    }
}