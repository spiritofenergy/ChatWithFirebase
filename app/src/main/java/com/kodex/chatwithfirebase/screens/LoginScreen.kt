package com.kodex.chatwithfirebase.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.MainViewModel
import com.kodex.chatwithfirebase.R
import com.kodex.chatwithfirebase.ui.theme.ChatWithFirebaseTheme
import com.kodex.chatwithfirebase.util.Constants
import com.kodex.chatwithfirebase.util.LOGIN
import com.kodex.chatwithfirebase.util.PASSWORD


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(viewModel: MainViewModel, navController: NavHostController) {

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    lateinit var auth: FirebaseAuth

    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.loadingState.collectAsState()
    val context = LocalContext.current

    // Equivalent of onActivityResult
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            viewModel.signWithCredential(credential)
        } catch (e: ApiException) {
            Log.w("TAG", "Google sign in failed", e)
        }
    }



    Scaffold(scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                TopAppBar(
                    backgroundColor = colorResource(id = R.color.colorPrimaryDark),
                    elevation = 1.dp,
                    title = {
                        Text(text = "Login")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate("chat_screen")
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Firebase.auth.signOut()
                            Toast.makeText(context,"Вы вышли из аккаунта", Toast.LENGTH_LONG).show()
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.ExitToApp,
                                contentDescription = null,
                            )
                        }
                    }
                )
             //  if (state.status == LoadingState.Status.RUNNING) {
              //      LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
              //  }
            }
        }, content = {
            Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.colorPrimary)).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                 horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    OutlinedTextField(value = login, onValueChange = { login = it }, modifier = Modifier.fillMaxWidth(), label = { Text(text = "Логин", color = Color.White)})

                    OutlinedTextField(value = password, onValueChange = { password = it }, modifier = Modifier.fillMaxWidth(), visualTransformation = PasswordVisualTransformation(), label = { Text(text = "Пароль", color = Color.White) })

                    Button(modifier = Modifier.fillMaxWidth().height(50.dp), enabled = login.isNotEmpty() && password.isNotEmpty(), content = {
                            Text(text = "Login") }, onClick = {
                                    LOGIN = login
                                    PASSWORD = password
                        Log.d("CheckData", "Pass: $password & Login: $login")
                        viewModel.initDatabase(){
                            navController.navigate(Constants.Screens.CHAT_SCREEN)
                        }
                           })
                    Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.caption, text = "Login with")






                    Spacer(modifier = Modifier.height(18.dp))

                    val context = LocalContext.current

                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            val gso = GoogleSignInOptions
                                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken("69878787268-1feb1jobb7ahbeq6kbi533s0msia54ib.apps.googleusercontent.com")
                                .requestEmail()
                                .build()
                            val googleSignInClient = GoogleSignIn.getClient(context, gso)
                            launcher.launch(googleSignInClient.signInIntent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            colorResource(
                                id = R.color.colorPrimaryDark
                            )),
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        tint = Color.Unspecified,
                                        painter = painterResource(id = com.google.android.gms.auth.api.R.drawable.googleg_standard_color_18),
                                        contentDescription = null,
                                    )
                                    Text(
                                        style = MaterialTheme.typography.button,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        text = "Google"
                                    )
                                    Icon(
                                        tint = Color.Transparent,
                                        imageVector = Icons.Default.MailOutline,
                                        contentDescription = null,
                                    )
                                }
                            )
                        }
                    )

                  /*  when(state.status) {
                        LoadingState.Status.SUCCESS -> {
                            Text(text = "Успешная регистрация")
                        }
                        LoadingState.Status.FAILED -> {
                            Text(text = state.msg ?: "Ошибка регистрации")
                        }
                        else -> {}
                    }*/
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    
    ChatWithFirebaseTheme(false) {
       // LoginScreen(navController = rememberNavController() )
    }
}