package com.kenshi.animereview.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.kenshi.animereview.data.PreferenceManager
import com.kenshi.animereview.data.model.User
import com.kenshi.animereview.data.network.service.FirebaseUserService
import com.kenshi.animereview.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

//@HiltViewModel
//class LoginViewModel @Inject constructor(
@HiltViewModel
class LoginViewModel @Inject constructor(
//    private val userRepository: UserRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {
    //val myStateLiveData = MutableLiveData<MyState>(MyState.Uninitialized)

    private val USER_PATH = "users"
    private var db = FirebaseFirestore.getInstance()

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun saveUserInfo(userId: String, userInfo: User) {
//            db.collection(USER_PATH).document(userId).set(userInfo).await()
        db.collection(USER_PATH)
            .document(userId)
            .set(userInfo)
            .addOnSuccessListener {
                _userLiveData.value = userInfo
                Timber.d("Save Success")
            }
            .addOnFailureListener {
                Timber.d("Save Fail")
            }
    }

    fun saveToken(idToken: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            preferenceManager.putIdToken(idToken)
        }
    }
}

