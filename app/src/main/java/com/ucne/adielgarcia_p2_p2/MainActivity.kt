package com.ucne.adielgarcia_p2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ucne.adielgarcia_p2_p2.presentation.navigation.P2NavHost
import com.ucne.adielgarcia_p2_p2.ui.theme.AdielGarcia_P2_P2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdielGarcia_P2_P2Theme {
                val navHost = rememberNavController()
                P2NavHost(navHost)
            }
        }
    }
}
