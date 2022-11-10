package com.example.composecamp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composecamp.Screens.*

@Composable
fun AppNavigator(navHostController: NavHostController) {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "Login1" ){

        composable("Login1"){
            Loginui1(navController,LocalContext.current)
        }
        composable("signup"){
            Loginui(navController,LocalContext.current)
        }
        composable("Dashboard/{userId}",arguments = listOf(navArgument("userId") { type = NavType.StringType })) {backStackEntry ->
            backStackEntry.arguments?.getString("userId")
                ?.let { AffirmationDashboard(navController, it) }
        }
        composable("DocDashboard"){
            AffirmationDocDashboard(navController)
        }
        composable("Cancel_Aptmnt"){
            CAAffirmationDashboard(navController,LocalContext.current)
        }
        composable("ReSchedule/{userId}",arguments = listOf(navArgument("userId") { type = NavType.StringType })) {backStackEntry ->
            backStackEntry.arguments?.getString("userId")
                ?.let {
            ReScheduleApt(navController,it)}
        }
        composable("ViewAppointmnt"){
            VAAffirmationDashboard(navController,LocalContext.current)
        }
        composable("Schedule"){
            AffirmationApp3()
        }
        composable("ScheduleAppointment/{userId}",arguments = listOf(navArgument("userId") { type = NavType.StringType })) { backStackEntry ->
            backStackEntry.arguments?.getString("userId")?.let { ScheduleApt(navController, it) }
        }
        composable("Announcement"){
            AffirmationApp5()
        }
        composable("DocLogin"){
            DocLogin(navController,LocalContext.current)
        }
        composable("DocCrtUsr"){
            DocCreateusr(navController,LocalContext.current)
        }
        composable("AdminDashboard"){
            AffirmationAdDashboard(navController)
        }
        composable("AdminLogin"){
            AdminLogin(navController,LocalContext.current)
        }
        composable("AdminCrtUsr"){
            AdminCreateusr(navController,LocalContext.current)
        }
        composable("AdminCreateUser"){
            AffirmationAdCreate(navController)
        }


    }


}