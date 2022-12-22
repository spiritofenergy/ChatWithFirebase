package com.kodex.chatwithfirebase.screens

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.R

@Composable
fun Chat() {
    // Write a message to the database
    val database = Firebase.database
    val myRef = database.getReference("message")

    myRef.setValue("Hello,ytyj World!!!")
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.colorPrimaryDark))
                .wrapContentSize(Alignment.BottomEnd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chat",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            OutlinedTextField(
                value = "Текст",
                onValueChange = {  },
                label = { Text(
                    color = Color.White,
                    text = "Введите текст") },
               // isError = isEmpty()
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                          Log.d("checkData","Button pressed")

                },
                colors = ButtonDefaults.buttonColors(
                    colorResource(
                        id = R.color.colorPrimary
                    )
                )
            )
            {
                Text(
                    text = "Send message",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }


        }
    }


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
       Chat()
}
