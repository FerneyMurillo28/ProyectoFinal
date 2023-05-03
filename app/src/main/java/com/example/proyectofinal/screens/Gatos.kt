package com.example.proyectofinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectofinal.models.GatosViewModel
import com.example.proyectofinal.navigation.AppNavigation
import com.example.proyectofinal.ui.theme.cooffe
import com.example.proyectofinal.ui.theme.marte

@Composable
fun Carta(navController: NavController, imagen:String,  nomgato:String, info:String, wiki:String) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
            .background(Color.DarkGray)
            .clickable { navController.navigate(route = AppNavigation.Info.createRoute(imagen,nomgato, info,wiki)) }
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            AsyncImage(
                model = imagen,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(2.dp))
                    .padding(all=5.dp)
                    .border(BorderStroke(5.dp,SolidColor(Color.White)))
            )
            Text(text = nomgato, color = Color.White, fontSize = 16.sp, fontFamily = marte)
        }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Neko(navController: NavController,viewModel:GatosViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gatos") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(modifier= Modifier.size(32.dp),
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color.White,
                onClick = { navController.navigate(route= AppNavigation.Add.route)}) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Agregar",
                    tint = Color.Black
                )
            }
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
        ) {
            Column() {
                LazyColumn() {
                    items(viewModel.gatos.value) { gato ->
                        Carta(navController, gato.imagen,gato.nomgato,gato.info,gato.wiki)
                    }
                }
            }//fincolum
        }//finbox
    }
}