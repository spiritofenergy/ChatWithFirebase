package com.kodex.chatwithfirebase.screens

import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.Surface
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getString
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth


import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.R

@Composable
fun Chat() {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth

    val database = Firebase.database
    val myRef = database.getReference("message")
    myRef.setValue("Hello,ytergeryj World!!!")


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GFG | Collapsing Toolbar",
                color = Color.White) },
                backgroundColor = (colorResource(R.color.colorPrimary))
            ) },
        )
    {
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomEnd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Chat",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            OutlinedTextField(
                value = "Текст",
                onValueChange = {  },
                label = { Text(
                    color = Color.Red,
                    text = "Введите текст") },
               // isError = isEmpty()
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), onClick = { Log.d("checkData","Button pressed") }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorPrimary))
            ) {
                Text(text = "Send message",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp) }

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                onClick = {
                    Log.d("checkData","Button pressed")
                          }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorPrimary))) {
                Text(text = "Google",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp) }
        }
    }


@Preview(showBackground = true)
@Composable
fun PrevChat(){
    Chat()
}

