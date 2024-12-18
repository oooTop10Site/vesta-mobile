package com.example.vesta.data.mapper.user

import com.example.vesta.data.models.user.Token
import com.example.vesta.data.models.user.TokenResponse
import com.example.vesta.domain.modelsUI.user.TokenUi

fun TokenResponse.toUI(): TokenUi {
    return TokenUi(
        plainTextToken = this.token?.plainTextToken.orEmpty()
    )
}

fun Token.toUI(): TokenUi {
    return TokenUi(
        plainTextToken = this.plainTextToken.orEmpty()
    )
}