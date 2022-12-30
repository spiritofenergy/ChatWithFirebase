package com.kodex.chatwithfirebase.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.MainViewModel
import com.kodex.chatwithfirebase.R
import com.kodex.chatwithfirebase.model.Note
import com.kodex.chatwithfirebase.util.Constants.Keys.SUBTITLE
import com.kodex.chatwithfirebase.util.Constants.Keys.TITLE
import com.squareup.picasso.Picasso

@Composable
fun ChatScreen(navController: NavController, viewModel: MainViewModel) {

    var title by remember { mutableStateOf("")}
    var subtitle by remember { mutableStateOf("")}

    //val database = Firebase.database
    //val myRef = database.getReference("message")
   // val myRefName = database.getReference("name")
   // myRef.setValue("Hello,ytergeryj World!!!")
   // var auth: FirebaseAuth = Firebase.auth
   // subtitle = auth.currentUser?.displayName.toString()
   //  name = stringResource(id = R.string.app_name)



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(subtitle,
                        color = Color.White
                    )
                },
            //    navigationIcon = { Picasso.get().load(auth.currentUser?.photoUrl) },
                backgroundColor = (colorResource(R.color.colorPrimary))
            )
        })
    { innerPadding ->
        Modifier.padding(innerPadding)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .wrapContentSize(Alignment.BottomEnd),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it},
            label = { Text(color = Color.Red, text = "Введите текст") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = subtitle,
            onValueChange = { subtitle = it},
            label = { Text(color = Color.Red, text = "Введите текст") },
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            onClick = {
              TITLE = title
                SUBTITLE = subtitle
                viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                    Log.d("checkData", "Button pressed $title & $subtitle") }
               // myRef.setValue(User(auth.currentUser?.displayName.toString()).toString())
              //  myRef.child(myRef.push().key ?: "Empty")
             //       .setValue(User(auth.currentUser?.displayName))

                      },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorPrimary))
        ) {
            Text(
                text = "Отправить сообения",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), onClick = {
            navController.navigate("login_screen")
        }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorPrimary))) {
            Text(
                text = "Регистрация",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PrevChat(){
    val navController = rememberNavController()
    ChatScreen(navController = navController, viewModel = viewModel())
}

