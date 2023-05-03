package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectofinal.navigation.AppNavigation
import com.example.proyectofinal.screens.LoginScreen
import com.example.proyectofinal.screens.Salir
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    companion object{
        const val RC_SIGN_IN=100
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth=FirebaseAuth.getInstance()

        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient= GoogleSignIn.getClient(this,gso)
        setContent {
            ProyectoFinalTheme {
                if (mAuth.currentUser==null){
                    LoginScreen {
                        signIn()
                    }
                }else{
                    val user: FirebaseUser =mAuth.currentUser!!
                    AppNavigation()
                    Salir(
                        profileImage = user.photoUrl!!,
                        name = user.displayName!!,
                        email = user.email!!,
                        signOutClick = {signOut()}
                    )
                }//finIf
            }//finTheme
        }//finSetContent
    }//finOnCreate
    private fun signIn(){
        val signInIntent=googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }//finsignIn

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN_IN){
            val task=GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception=task.exception
            if (task.isSuccessful){
                try {
                    val account=task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                }catch (e:Exception){
                    Log.d("SignIn","Google Sign Failed")
                }//finTry
            }else{
                Log.d("Sign In", exception.toString())
            }//finif2
        }//finIf
    }//finonActivityResult
    private fun firebaseAuthWithGoogle(idToken:String){
        val credential= GoogleAuthProvider.getCredential(idToken,null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){task->
                if (task.isSuccessful){
                    Toast.makeText(this, "Sign In Succesgul", Toast.LENGTH_SHORT).show()
                    setContent{
                        ProyectoFinalTheme {
                            val user: FirebaseUser=mAuth.currentUser!!
                            AppNavigation()
                            Salir(
                                profileImage = user.photoUrl!!,
                                name = user.displayName!!,
                                email = user.email!!,
                                signOutClick = {
                                    signOut()
                                }
                            )
                        }//finTheme
                    }//finSetContent
                }else{
                    Toast.makeText(this,"Sign In failed", Toast.LENGTH_SHORT).show()
                }//finIf
            }//finaddOn
    }//finFirebaseAuthWithGoogle
    private fun signOut(){
        val googleSignInClient:GoogleSignInClient
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this,gso)
        mAuth.signOut()
        googleSignInClient.signOut().addOnSuccessListener {
            Toast.makeText(this, "Sign Out Succes", Toast.LENGTH_SHORT).show()
            setContent {
                ProyectoFinalTheme{
                    LoginScreen {
                        signIn()
                    }
                }
            }
        }
            .addOnFailureListener {
                Toast.makeText(this,"Sign Out Failed", Toast.LENGTH_SHORT).show()
            }
    }
}//finMainActivity