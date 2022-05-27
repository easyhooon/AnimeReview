package com.kenshi.animereview.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
data class User(val userId: String? = "", //Document ID is actually the user id
                val name: String? = "",
                val email: String? = "",
                val profileImageUrl: String? = "") : Parcelable {

    companion object {
        fun DocumentSnapshot.toUser(): User? {
            return try {
                val name = getString("name")!!
                val email = getString("email")!!
                val imageUrl = getString("profile_image_url")!!
                User(id, name, email, imageUrl)
            } catch (e: Exception) {
                Timber.e(e, "Error converting user profile")
                null
            }
        }
    }
}