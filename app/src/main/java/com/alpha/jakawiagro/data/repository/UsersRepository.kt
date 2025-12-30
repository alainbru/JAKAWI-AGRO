package com.alpha.jakawiagro.data.repository

import com.alpha.jakawiagro.data.model.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsersRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val col = db.collection("users")

    suspend fun createProfile(profile: UserProfile) {
        col.document(profile.uid).set(profile).await()
    }

    suspend fun getProfile(uid: String): UserProfile? {
        val snap = col.document(uid).get().await()
        return snap.toObject(UserProfile::class.java)
    }

    suspend fun updateProfile(profile: UserProfile) {
        col.document(profile.uid).set(profile.copy(updatedAt = System.currentTimeMillis())).await()
    }
}

