package com.kenshi.animereview.data.remote

import com.kenshi.animereview.data.model.User
import com.kenshi.animereview.data.network.service.FirebaseReviewService
import com.kenshi.animereview.data.network.service.FirebaseUserService
import com.kenshi.animereview.domain.repository.UserRepository
import com.kenshi.animereview.exceptions.EmptyBodyException
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: FirebaseUserService,
    private val reviewService: FirebaseReviewService
) : UserRepository {
    override suspend fun saveUserInfo(userId: String, userInfo: User){
        userService.setUserInfo(userId, userInfo)
    }

    //TODO 예외처리
    override fun fetchUserInfo(userId: String): Flow<UiState<User>> = flow<UiState<User>> {
        val userInfo = userService.getUserInfo(userId)
        Timber.tag("userInfo").d("$userInfo")
        emit(UiState.Success(userInfo!!))
    }.catch { emit(UiState.Error(it)) }
}