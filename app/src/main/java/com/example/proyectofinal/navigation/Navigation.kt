package com.example.proyectofinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.models.GatosViewModel
import com.example.proyectofinal.screens.Add
import com.example.proyectofinal.screens.Info
import com.example.proyectofinal.screens.Neko

@Composable
fun AppNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigation.Neko.route){
        composable(route = AppNavigation.Neko.route){ Neko(navController,GatosViewModel())}
        composable(route = AppNavigation.Add.route){ Add(navController)}
        composable(route = AppNavigation.Info.route, arguments = listOf(
            navArgument("imagen"){type= NavType.StringType},
            navArgument("nomgato"){type= NavType.StringType},
            navArgument("info"){type= NavType.StringType},
            navArgument("wiki"){type= NavType.StringType}
        )){
            Info(
                navController,
                imagen=it.arguments?.getString("imagen")?:"",
                nomgato=it.arguments?.getString("nomgato")?:"",
                info=it.arguments?.getString("info")?:"",
                wiki=it.arguments?.getString("wiki")?:""
            )
        }
    }
}