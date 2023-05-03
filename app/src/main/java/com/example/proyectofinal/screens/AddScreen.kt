package com.example.proyectofinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal.models.Gatos
import com.example.proyectofinal.navigation.AppNavigation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Add(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gatos") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(modifier= Modifier.size(32.dp),
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color.Black,
                onClick = {navController.navigate(route = AppNavigation.Neko.route)}) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Agregar",
                    tint = Color.Black
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController){
    var imagen by remember { mutableStateOf("") }
    var nomgato by remember { mutableStateOf("") }
    var info by remember { mutableStateOf("") }
    var wiki by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
        ) {
            TextField(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(),
                value = imagen,
                onValueChange = { imagen = it },
                label = { Text(text = "Imagen Url",color = Color.Black) },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth() ,
                value = nomgato,
                onValueChange ={nomgato=it},
                label = { Text(text = "Nombre",color = Color.Black) },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth() ,
                value = info,
                onValueChange ={info=it},
                label = { Text(text = "Info",color = Color.Black) },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(modifier = Modifier
                .background(Color.White)
                .fillMaxWidth() ,
                value = wiki,
                onValueChange ={wiki=it},
                label = { Text(text = "Link Wiki",color = Color.Black) },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),

                onClick = {
                val gato= Gatos(imagen, nomgato, info, wiki)
                Firebase.firestore.collection("gatos").add(gato)
                navController.navigate(route = AppNavigation.Neko.route)
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Agregar Gato")
            }
        }
    }
}

