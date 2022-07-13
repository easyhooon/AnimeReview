package com.kenshi.animereview.domain.repository

import com.kenshi.animereview.data.model.user.User
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveUserInfo(userId: String, userInfo: User)

    fun fetchUserInfo(userId: String): Flow<UiState<User>>
}