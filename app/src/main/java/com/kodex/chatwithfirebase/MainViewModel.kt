package com.kodex.chatwithfirebase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.firebase.AppFirebaseRepository
import com.kodex.chatwithfirebase.model.Note
import com.kodex.chatwithfirebase.util.LoadingState
import com.kodex.chatwithfirebase.util.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainViewModel(application: Application): AndroidViewModel(application) {
    val context = application
    fun initDatabase(onSuccess: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(
            {onSuccess()},
            { Log.d("CheckData", "Error: ${it}")}
        )

    }
    fun addNote (note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.create(note = note){
                Log.d("MyLog", "addNote")
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
    fun reedAllNotes() = REPOSITORY.readAll

    val loadingState  = MutableStateFlow(LoadingState.IDLE)

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Log.d("MyLog", "$email & $password")
            loadingState.emit(LoadingState.LOADED)
        } catch (e:Exception){
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()

        } catch (e:Exception){
            loadingState.emit(LoadingState.error(e.localizedMessage))
        }
    }
    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithCredential(credential).await()
            loadingState.emit(LoadingState.LOADED)
        }catch (e: Exception){
            loadingState.emit(LoadingState.error(e.localizedMessage))
        }

    }


}