package com.ucne.adielgarcia_p2_p2.presentation.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ucne.adielgarcia_p2_p2.presentation.depositos.form.DepositoScreen
import com.ucne.adielgarcia_p2_p2.presentation.depositos.list.DepositosListScreen
import com.ucne.adielgarcia_p2_p2.utils.DepositoNavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun P2NavHost(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.DepositosList
    ){
        composable<Screen.DepositosList> {
            DepositosListScreen(
                onDepositoClick = {depositoClickeado ->
                    val formattedDto = Json.encodeToString(depositoClickeado)
                    navHostController.navigate("depositoScreen/${formattedDto}")
                    Log.d("DTO",depositoClickeado.toString())
                    Log.d("Formatted DTO",formattedDto)
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
            val deposito = it.arguments?.getString("deposito")
            Log.d("TAG",deposito?:"")
            DepositoScreen(deposito = deposito,onBack = { navHostController.navigate(Screen.DepositosList) })
        }

        composable(route="depositoScreen/new"
        ) {
            DepositoScreen(deposito = null,onBack = { navHostController.navigate(Screen.DepositosList) })
        }
    }
}