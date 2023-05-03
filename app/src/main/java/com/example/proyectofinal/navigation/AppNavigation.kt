package com.example.proyectofinal.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class AppNavigation(val route:String){
    object Add:AppNavigation(route = "AddScreen")
    object Neko:AppNavigation(route = "Gatos")
    object Info:AppNavigation(route = "Informacion/{imagen}/{nomgato}/{info}/{wiki}"){
        fun createRoute(imagen:String,nomgato: String, info: String, wiki:String ): String {
            fun String.encodeUrl() = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
            return "Informacion/${imagen.encodeUrl()}/$nomgato/$info/${wiki.encodeUrl()}"
        }
    }
}
