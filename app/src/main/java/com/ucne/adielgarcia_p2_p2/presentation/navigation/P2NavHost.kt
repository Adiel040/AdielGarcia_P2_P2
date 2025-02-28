package com.ucne.adielgarcia_p2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import com.ucne.adielgarcia_p2_p2.presentation.depositos.form.DepositoScreen
import com.ucne.adielgarcia_p2_p2.presentation.depositos.list.DepositosListScreen
import com.ucne.adielgarcia_p2_p2.utils.DepositoNavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun P2NavHost(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.DepositosList
    ){
        composable<Screen.DepositosList> {
            DepositosListScreen(
                onDepositoClick = {depositoClickeado ->
                    navHostController.navigate("depositoScreen/${Json.encodeToString(depositoClickeado)}")
                },
                onNewClick = {
                    navHostController.navigate("depositoScreen/new")
                }
            )
        }
        composable(route="depositoScreen/{deposito}", arguments = listOf(
            navArgument("deposito") {
                type = DepositoNavType()
            }
        )) {
            @Suppress("DEPRECATION") val deposito = it.arguments?.get("deposito") as? DepositoDto
            DepositoScreen(deposito = deposito,onBack = { navHostController.navigate(Screen.DepositosList) })
        }

        composable(route="depositoScreen/new"
        ) {
            DepositoScreen(deposito = null,onBack = { navHostController.navigate(Screen.DepositosList) })
        }
    }
}