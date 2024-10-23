package com.example.vesta.screen.home

import com.example.vesta.domain.repository.InfoRepository
import com.example.vesta.domain.repository.ProductRepository
import com.example.vesta.platform.BaseScreenModel
import com.example.vesta.platform.OpenPhone
import kotlinx.coroutines.delay
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class HomeViewModel: BaseScreenModel<HomeState, Unit>(HomeState.InitState) {

    private val infoRepository: InfoRepository by inject()

    fun loadData(){
        loadStocks()
    }

    fun loadStocks() = intent{
        launchOperation(
            operation = {
                infoRepository.getStocks()
            },
            success = { response ->
                reduceLocal { state.copy(
                    stockData = response
                ) }
            }
        )
    }

    fun openPhone() = intent {
        reduce{ state.copy(isOpenPhone = true) }
        delay(500)
        reduce{ state.copy(isOpenPhone = false) }
    }

    fun openSity() = intent {
        reduce{ state.copy(isOpenSity = !state.isOpenSity) }

    }
}