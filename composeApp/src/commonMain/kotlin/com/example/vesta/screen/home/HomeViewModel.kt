package com.example.vesta.screen.home

import com.example.vesta.domain.manager.AuthManager
import com.example.vesta.domain.manager.ObserverManager
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
    private val productRepository: ProductRepository by inject()
    private val manager: AuthManager by inject()
    private val bottomBarVisibleManager: ObserverManager by inject()

    fun loadData(){
        loadStocks()
    }

    fun loadStocks() = intent{
        launchOperation(
            operation = {
                infoRepository.getMainBlogs()
            },
            success = { response ->
                reduceLocal { state.copy(
                    stockData = response.akcii,
                    stockSliderData = response.akciiSlider,
                    newsData = response.novosti,
                    newProductsData = response.novinki
                ) }
            }
        )
        launchOperation(
            operation = {
                productRepository.getFeaturedProducts()
            },
            success = {response ->
                reduceLocal {
                    state.copy(
                        productList = response
                    )
                }},
        )
        launchOperation(
            operation = {
                infoRepository.getCites()
            },
            success = { response ->
                var phone: String = ""
                for(item in response){
                    if(item.storeId==manager.city){
                        phone = item.phone
                    }
                }
                reduceLocal {
                    state.copy(
                        phone = phone
                    )
                }
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

    fun updatePage(index: Int) = blockingIntent{
        reduce{ state.copy(selectedPage = index) }
    }

    fun setBottomBarVisible(visible: Boolean){
        bottomBarVisibleManager.setBottomBarVisibility(visible)
    }

    fun addToWishlist(id: Int) = intent {
        launchOperation(
            operation = {
                productRepository.addToWishlist(id)
            },
        )
    }
}
