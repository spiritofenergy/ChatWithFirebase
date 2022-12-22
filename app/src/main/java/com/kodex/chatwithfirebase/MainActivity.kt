package com.kodex.chatwithfirebase

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.databinding.ActivityMainBinding
import com.kodex.chatwithfirebase.screens.Chat
import com.kodex.chatwithfirebase.ui.theme.ChatWithFirebaseTheme
import androidx.activity.compose.setContent
import com.kodex.chatwithfirebase.screens.LoginScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChatWithFirebaseTheme {
                LoginScreen()
            }
        }
    }
}
/* lateinit var binding: ActivityMainBinding
lateinit var auth: FirebaseAuth
private lateinit var launcher: ActivityResultLauncher<Intent>
private val text = "Вы зарегистрированы!"
private val already = "Вы уже зарегистрированы!"
private val duration = Toast.LENGTH_SHORT
lateinit var adapter: UserAdapter

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.myToolbar)
    setUpActionBar()

    val database = Firebase.database
    val myRef = database.getReference("message")

    binding.bSend.setOnClickListener {
        myRef.child(myRef.push()
            .key ?: "Empty").setValue(User(auth.currentUser?.displayName,
            binding.edMessage.text.toString()))
    }
    onChangeListener(myRef)
    initRcView()

    auth = Firebase.auth
    launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                firebaseAuthWithGoogle(account.idToken!!)
                Log.d("MyLog", "Api Ok")
            }
        } catch (e: ApiException) {
            Log.d("MyLog", "Api Exception2")
        }
    }
    binding.signIn.setOnClickListener {
        signInWithGoogle()
        Toast.makeText(this, text, duration).show()
    }
}
private fun initRcView() = with(binding){
    adapter = UserAdapter()
    rcView.layoutManager = LinearLayoutManager(this@MainActivity)
    rcView.adapter = adapter
}
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
}
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(item.itemId == R.id.sign_out){
        auth.signOut()
    }
    return super.onOptionsItemSelected(item)
}
private   fun getClient(): GoogleSignInClient {
    val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(this, gso)
}
private fun signInWithGoogle(){
    val signInClient = getClient()
    launcher.launch(signInClient.signInIntent)
}
private fun firebaseAuthWithGoogle(idToken: String){
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential).addOnCompleteListener{
        if (it.isSuccessful){
            Log.d("MyLog","Google signIn done")
            checkAuthState()

        }else{
            Log.d("MyLog","Google signIn Error")
        }
    }
}

private fun onChangeListener(dRef: DatabaseReference){
    dRef.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = ArrayList<User>()
            for (s in snapshot.children){
                val user = s.getValue(User::class.java)
                if(user != null)list.add(user)
            }
            adapter.submitList(list)
        }
        override fun onCancelled(error: DatabaseError) {
        }
    })
}
private fun checkAuthState(){
    if (auth.currentUser != null){
        Toast.makeText(this, already, duration).show()

        val i = Intent(this, MainActivity::class.java)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatWithFirebaseTheme {
    }
}
private fun setUpActionBar(){
    val ab = supportActionBar
    Thread{
        val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
        val dIcon = BitmapDrawable(resources, bMap)
        runOnUiThread{
            ab?.setDisplayHomeAsUpEnabled(true)
            ab?.setHomeAsUpIndicator(dIcon)
            ab?.title = auth.currentUser?.displayName
            Log.d("CheckData","title: ${ab?.title}")
        }
    }.start()
}
}
*/