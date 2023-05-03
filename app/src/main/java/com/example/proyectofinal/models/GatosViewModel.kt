package com.example.proyectofinal.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class GatosViewModel:ViewModel() {
    private val _gatos= mutableStateOf<List<Gatos>>(emptyList())
    val gatos:State<List<Gatos>>
        get() =_gatos
    private val query=Firebase.firestore.collection("gatos")
    init {
        query.addSnapshotListener{value,_->
            if (value!=null){
                _gatos.value=value.toObjects()
            }
        }
    }
}