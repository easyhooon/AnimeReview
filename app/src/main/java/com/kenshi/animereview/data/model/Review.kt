package com.kenshi.animereview.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize
import timber.log.Timber

//TODO DB 에는 animeId만 넣어두는 것이 맞다고 본다. id를 통해 다른 정보들을 요청할 수 있으므로
//DB 에는 animeId, userId, rating, review 만 저장
//데이터 구조 변경해야..
//파이어스토어으이 장점: 내 현재 필드의 id를 안들고 있어도 된다.

@Parcelize
data class Review(val reviewId: String? = null,
                  val animeId: String? = null,
                  val userId: String? = null,
                  var rating: String? = null,
                  val reviewText: String? = null
) : Parcelable {
   companion object {
       fun DocumentSnapshot.toReview(): Review? {
           return try {
               //val reviewId = getString("review_id")!!
               val animeId = getString("anime_id")!!
               val userId = getString("user_id")!!
               val rating = getString("profile_image")!!
               val reviewText = getString("review_text")!!
               Review(id, animeId, userId, rating, reviewText)
           } catch (e: Exception) {
               Timber.e(e, "Error converting review")
               null
           }
       }

       val EMPTY = Review
   }

}