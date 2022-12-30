package com.kodex.chatwithfirebase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodex.chatwithfirebase.firebase.AppFirebaseRepository
import com.kodex.chatwithfirebase.model.Note
import com.kodex.chatwithfirebase.util.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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




}