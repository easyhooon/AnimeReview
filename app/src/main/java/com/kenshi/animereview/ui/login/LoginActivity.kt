package com.kenshi.animereview.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.MainActivity
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.User
import com.kenshi.animereview.databinding.ActivityLoginBinding
import com.kenshi.animereview.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    val viewModel: LoginViewModel by viewModels()

    private val gso: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    //Google SignIn Client
    private val gsc by lazy { GoogleSignIn.getClient(this, gso) }

    //firebase auth 객체 관리
    private lateinit var firebaseAuth: FirebaseAuth

    //startActivityForResult 대체
    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    //exception 대응
                    task.getResult(ApiException::class.java)?.let { account ->
                        Timber.tag("firebaseAuthWithGoogle").d(account.id)
                        handleLoginState(account.idToken ?: throw Exception())
                    } ?: throw Exception()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    //자동 로그인
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
        initViews()

        viewModel.userLiveData.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun initViews() {
        binding.btnGoogleLogin.setOnClickListener {
            loginWithGoogle()
        }
    }

    private fun loginWithGoogle() {
        val signInIntent: Intent = gsc.signInIntent
        loginLauncher.launch(signInIntent)
    }

    private fun handleLoginState(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser!!
                    viewModel.saveUserInfo(
                        user.uid,
                        User(
                            userId = user.uid,
                            name = user.displayName,
                            email = user.email,
                            profileImageUrl = user.photoUrl.toString()
                        )
                    )
                }
            }
    }
}