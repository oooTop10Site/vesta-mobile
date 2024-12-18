package com.example.vesta.screen.signIn

import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.vesta.domain.manager.AuthManager
import com.example.vesta.domain.manager.ObserverManager
import com.example.vesta.domain.repository.UserRepository
import com.example.vesta.ext.isValidEmail
import com.example.vesta.platform.BaseScreenModel
import com.example.vesta.screen.splash.SplashScreen
import com.example.vesta.strings.VestaResourceStrings
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class SignInViewModel:BaseScreenModel<SignInState, Unit>(SignInState.InitState) {

    private val userRepository: UserRepository by inject()
    private val authManager: AuthManager by inject()
    private val bottomBarVisibleManager: ObserverManager by inject()

    fun updatePassword(newPassword: String) = blockingIntent {
        reduce { state.copy(password = newPassword) }
    }

    fun updateEmail(newEmail: String) = blockingIntent {
        reduce { state.copy(email = newEmail) }
    }

    fun authorize(login: String, password: String, navigator: Navigator) = intent {

        if(login.isEmpty()|| password.isEmpty()) {
            reduce {
                state.copy(
                    errorPassword = if(password.isEmpty()) VestaResourceStrings.error_fill_all_fields else "",
                    errorEmail = if(login.isEmpty()) VestaResourceStrings.error_fill_all_fields else ""
                )
            }
        }
        else if(!login.isValidEmail()) {
            reduce {
                state.copy(
                    errorEmail = VestaResourceStrings.error_invalid_email
                )
            }
        }
        else{
            launchOperation(
                operation = {
                    userRepository.authorize(login, password)
                },
                success = { response ->
                    logoutNullableUser(response.plainTextToken, navigator)
//                    authManager.token = response.plainTextToken
//                    setBottomBarVisible(true)
//                    navigator.push(SplashScreen())
                },
                failure = { failure ->
                    when(failure.message){
                        "User not found" -> {
                            reduceLocal { state.copy(
                                errorPassword = VestaResourceStrings.error_user_not_found,
                                errorEmail = VestaResourceStrings.error_user_not_found
                            ) }
                        }
                        "Wrong password" -> {
                            reduceLocal { state.copy(
                                errorPassword = VestaResourceStrings.error_invalid_password
                            ) }
                        }
                        else -> {
                            reduceLocal { state.copy(
                                errorPassword = VestaResourceStrings.error_sign_in,
                                errorEmail = VestaResourceStrings.error_sign_in
                            ) }
                        }
                    }
                }
            )
        }
    }

    fun setBottomBarVisible(visible: Boolean){
        bottomBarVisibleManager.setBottomBarVisibility(visible)
    }

    fun updateTabNavigator(navigator: TabNavigator) = blockingIntent {
        reduce { state.copy(tabNavigator = navigator) }
    }

    fun isTabNavigator() : Boolean{
        return bottomBarVisibleManager.isTabNavigator()
    }

    private fun logoutNullableUser(newToken: String, navigator: Navigator) = intent {
        launchOperation(
            operation = {
                userRepository.logOut()
            },
            success = {
                authManager.token = newToken
                setBottomBarVisible(true)
                navigator.push(SplashScreen())
            }
        )
    }
}