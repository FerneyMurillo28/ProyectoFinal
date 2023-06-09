package com.example.proyectofinal.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.theme.cooffe

@Composable
fun LoginScreen(
    signInClick:()->Unit
){
    val icono= painterResource(R.drawable.gatoicon)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            stringResource(R.string.Login_Title),
            style =MaterialTheme.typography.h3, fontFamily = cooffe
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 32.dp)
            , painter = icono,
            contentDescription = null
        )
        Text(
            text = "Entrar Con:",
            style= MaterialTheme.typography.h5
        )
        Card(
            modifier = Modifier
                .padding(start = 20.dp, end = 25.dp)
                .height(55.dp)
                .width(70.dp)
                .clickable {
                    signInClick()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.5.dp, color = Color.Black),
            elevation = 5.dp
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(0.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.googleicon),
                    contentDescription = "google_logo"
                )
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
    }
}