package com.example.proyectofinal.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Salir(
    profileImage: Uri,
    name: String,
    email: String,
    signOutClick: () -> Unit
){
    var showMenu by remember {
        mutableStateOf(false)
    }
        TopAppBar(
            backgroundColor=Color.Black,
            contentColor = Color.White,
            title = { Text(text = "NekoPedia")},
            actions = {
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu=false },
                    modifier = Modifier
                        .width(250.dp)
                        .height(260.dp)
                ) {
                    DropdownMenuItem(onClick = { /*todo*/ }) {
                        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .clip(shape = CircleShape),
                                model = profileImage,
                                contentDescription = "profile_photo",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                    DropdownMenuItem(onClick = { /*todo*/ }) {
                        Text(text = name)
                    }
                    DropdownMenuItem(onClick = { /*todo*/ }) {
                        Text(text = email)
                    }
                    DropdownMenuItem(onClick =  signOutClick ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        Text(text = "Salir")
                    }
                }
            }
        )
}

