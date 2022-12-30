package com.kodex.chatwithfirebase.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kodex.chatwithfirebase.DatabaseRepository
import com.kodex.chatwithfirebase.model.Note
import com.kodex.chatwithfirebase.util.Constants
import com.kodex.chatwithfirebase.util.FIREBASE_ID
import com.kodex.chatwithfirebase.util.LOGIN
import com.kodex.chatwithfirebase.util.PASSWORD

class AppFirebaseRepository: DatabaseRepository {

    private val auth = FirebaseAuth.getInstance()

    private val database = Firebase.database.reference
        .child(auth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        super.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFile: (String) -> Int) {
        auth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                auth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{onFile(it.message.toString())}
            }
    }
}