package com.kodex.chatwithfirebase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.kodex.chatwithfirebase.firebase.AppFirebaseRepository
import com.kodex.chatwithfirebase.util.REPOSITORY

class MainViewModel(application: Application): AndroidViewModel(application) {
    val context = application
    fun initDatabase(onSuccess: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(
            {onSuccess()},
            { Log.d("CheckData", "Error: ${it}")}
        )

    }
}