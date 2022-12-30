package com.kodex.chatwithfirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodex.chatwithfirebase.screens.ChatScreen
import com.kodex.chatwithfirebase.screens.LoginScreen
import com.kodex.chatwithfirebase.util.Constants

sealed class NavRoute(var route: String) {
    object Login: NavRoute(route = Constants.Screens.LOGIN_SCREEN)
    object Chat: NavRoute(route = Constants.Screens.CHAT_SCREEN)
}
@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel){
    NavHost(navController = navController, startDestination = NavRoute.Login.route){
        composable(NavRoute.Chat.route){
            ChatScreen(navController = navController, viewModel = viewModel)
        }
        composable(NavRoute.Login.route){
            LoginScreen(navController = navController, viewModel = viewModel)
        }
    }
}