package com.example.proyectofinal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectofinal.funtions.HyperlinkText
import com.example.proyectofinal.navigation.AppNavigation
import com.example.proyectofinal.ui.theme.empo
import com.example.proyectofinal.ui.theme.marte
import com.example.proyectofinal.ui.theme.sick

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Info(navController: NavController, imagen:String,  nomgato:String, info:String, wiki:String){
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
                onClick = { navController.navigate(route = AppNavigation.Neko.route)}) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Agregar",
                    tint = Color.Black
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(all = 10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(imagen),
                contentDescription = null,
                modifier = Modifier
                    .size(270.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .border(BorderStroke(5.dp, SolidColor(Color.White))),
                contentScale = ContentScale.FillBounds
            )
            LazyColumn() {
                item {
                    Text(
                        text = nomgato.toString(),
                        fontSize = 40.sp,
                        color = Color.White,
                        fontFamily = marte,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        textAlign = TextAlign.Justify,
                        text = info.toString(),
                        fontFamily = empo,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        color =   Color.Black
                    )
                    HyperlinkText(
                        fullText = "Wikipedia",
                        linkText = listOf("Wikipedia") ,
                        hyperlinks = listOf(wiki))
                }
            }
        }
    }
}