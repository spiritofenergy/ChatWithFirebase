package com.kodex.chatwithfirebase.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kodex.chatwithfirebase.MainViewModel
import com.kodex.chatwithfirebase.R
import com.kodex.chatwithfirebase.model.Note
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.util.*
import com.kodex.chatwithfirebase.util.Constants.Keys.MESSAGE
import com.kodex.chatwithfirebase.util.Constants.Keys.NAME


@Composable
fun ChatScreen(navController: NavController, viewModel: MainViewModel) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("")}
    var message by remember { mutableStateOf("")}

        val notes = viewModel.reedAllNotes().observeAsState(listOf()).value
   // val database = Firebase.database
    //val myRef = database.getReference("message")
   // val myRefName = database.getReference("name")
   // myRef.setValue("Hello,ytergeryj World!!!")
    var auth: FirebaseAuth = Firebase.auth
   // subtitle = auth.currentUser?.displayName.toString()
    // name = stringResource(id = R.string.app_name)
    name = auth.currentUser?.displayName.toString()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(name,
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

    LazyColumn(modifier = Modifier.padding(vertical = 60.dp)){
        items(notes) { note ->
            NoteItem( note = note, navController = navController as NavHostController)
        }
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
            value = message,
            onValueChange = { message = it},
            label = { Text(color = Color.Red, text = "Введите текст") },
            isError = message.isEmpty()
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            onClick = {
            // message = auth.currentUser?.displayName.toString()
                viewModel.addNote(note = Note(name = name, message = message)) {
                    Log.d("checkData", "Button pressed $name & $message")
                    Log.d("checkData", "Button pressed ${auth.currentUser?.displayName} & $message") }
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



@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    val noteId  = null
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        elevation = 6.dp
    ){
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.name,
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                color = Color.Black,
                text = note.message,
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

