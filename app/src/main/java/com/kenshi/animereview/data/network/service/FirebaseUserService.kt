package com.kenshi.animereview.data.network.service

import com.google.firebase.firestore.FirebaseFirestore
import com.kenshi.animereview.data.model.user.User
import com.kenshi.animereview.data.model.user.User.Companion.toUser
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Singleton

@Singleton
object FirebaseUserService {
    private const val TAG = "FirebaseUserService"
    private const val USER_PATH = "users"

    suspend fun getUserInfo(userId: String): User? {
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection(USER_PATH)
                .document(userId).get().await().toUser()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Error getting userInfo")
            null
        }
    }

    suspend fun setUserInfo(userId: String, userInfo: User) {
        val db = FirebaseFirestore.getInstance()
        try {
            db.collection(USER_PATH).document(userId).set(userInfo).await()
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Error setting userInfo")
        }
    }
}