package com.example.vesta.domain.repository

import com.example.vesta.data.models.info.SityUi
import com.example.vesta.domain.modelsUI.CategoryUi
import com.example.vesta.domain.modelsUI.info.ShopsUi
import com.example.vesta.domain.modelsUI.info.StocksUi
import com.example.vesta.domain.modelsUI.user.CurrentUserUi
import com.example.vesta.domain.modelsUI.user.TokenUi
import com.example.vesta.platform.Either
import com.example.vesta.platform.Failure

interface UserRepository {

    suspend fun autirize(login: String, password: String) : Either<Failure,TokenUi>

    suspend fun getCurrentUser() : Either<Failure,CurrentUserUi>

    suspend fun logOut() : Either<Failure,Unit>

}