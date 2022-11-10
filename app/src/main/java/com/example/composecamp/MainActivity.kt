package com.example.composecamp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.example.composecamp.Screens.*
import com.example.composecamp.ui.theme.ComposecampTheme
import com.example.composecamp.navigation.AppNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposecampTheme {
              AppNavigator(navHostController = NavHostController(applicationContext))
            }

        }
    }
}
