package com.example.vesta.screen.newsDetails

import com.example.vesta.domain.modelsUI.blog.BlogByIdUi

data class NewsDetailsState(
    val newsData: BlogByIdUi,
){
    companion object{
        val InitState = NewsDetailsState(
            newsData = BlogByIdUi.empty
        )
    }
}
