package com.kenshi.animereview.data.network.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.data.model.Review.Companion.toReview
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Singleton

@Singleton
object FirebaseReviewService {
    private const val TAG = "FirebaseReviewService"

    // 이거는 firebase query 이용해야함
//    fun getUserReviews(userId: String): Flow<List<Review>> {
//        val db = FirebaseFirestore.getInstance()
//        return callbackFlow {
//            val listenerRegistration = db.collection("reviews")
//                .document(userId)
//                .collection("reviews")
//                .addSnapshotListener { querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
//                    if (firebaseFirestoreException != null) {
//                        cancel(message = "Error fetching posts",
//                            cause = firebaseFirestoreException)
//                        return@addSnapshotListener
//                    }
//                    val map = querySnapshot?.documents?.mapNotNull { it.toReview() }!!
//                    //offer(map) is deprecated
//                    trySend(map)
//                }
//            awaitClose {
//                Timber.d("Cancelling reviews listener")
//                listenerRegistration.remove()
//            }
//        }
//    }

//    fun getAnimeReviews(animeId: String): Flow<List<Review>> {
//        val db = FirebaseFirestore.getInstance()
//        return callbackFlow {
//            val listenerRegistration = db.collection("reviews")
//                .document(userId)
//                .collection("reviews")
//                .addSnapshotListener { querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
//                    if (firebaseFirestoreException != null) {
//                        cancel(message = "Error fetching posts",
//                            cause = firebaseFirestoreException)
//                        return@addSnapshotListener
//                    }
//                    val map = querySnapshot?.documents?.mapNotNull { it.toReview() }!!
//                    //offer(map) is deprecated
//                    trySend(map)
//                }
//            awaitClose {
//                Timber.d("Cancelling reviews listener")
//                listenerRegistration.remove()
//            }
//        }
//    }
}