package com.kodex.chatwithfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.databinding.ActivityMainBinding
import com.kodex.chatwithfirebase.ui.theme.ChatWithFirebaseTheme



class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference("message")

        binding.bSend.setOnClickListener {
            myRef.setValue(binding.edMessage.text.toString())
       }
        onChangeListener(myRef)
    }
    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    rcView.append("\n")
                    rcView.append("Maric: ${snapshot.value.toString()}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatWithFirebaseTheme {
     }
}
    }

/*  setContent {
          ChatWithFirebaseTheme {
             Chat()
          }
      }*/