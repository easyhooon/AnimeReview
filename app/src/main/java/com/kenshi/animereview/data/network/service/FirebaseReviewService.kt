package com.kenshi.animereview.data.network.service

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