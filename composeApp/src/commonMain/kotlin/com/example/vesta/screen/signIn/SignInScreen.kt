package com.example.vesta.screen.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.example.vesta.components.CustomButton
import com.example.vesta.components.CustomScaffold
import com.example.vesta.components.CustomSplitClickableText
import com.example.vesta.components.HeaderWithButtonBack
import com.example.vesta.components.RoundedTextField
import com.example.vesta.images.VestaResourceImages
import com.example.vesta.screen.home.HomeScreen
import com.example.vesta.screen.mainTab.MainTabScreen
import com.example.vesta.screen.profile.ProfileScreen
import com.example.vesta.screen.signUp.SignUpScreen
import com.example.vesta.screen.tabs.HomeTab
import com.example.vesta.strings.VestaResourceStrings
import io.github.skeptick.libres.compose.painterResource

class SignInScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SignInViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        if(viewModel.isTabNavigator()){
            viewModel.updateTabNavigator(LocalTabNavigator.current)
        }

        LifecycleEffect(
            onStarted = {
                viewModel.setBottomBarVisible(false)
            }
        )
        CustomScaffold(
            topBar = {
                HeaderWithButtonBack(
                    onClick =
                    {
                        if(navigator.canPop){
                            navigator.pop()
                        }
                        else{
                            viewModel.setBottomBarVisible(true)
                            state.tabNavigator?.current = HomeTab
                        }
                    },
                    text = VestaResourceStrings.authorize
                )
            },
            bottomBar = {
                Box(Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp, start = 20.dp, end = 20.dp)
                ){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CustomButton(
                            onClick = {viewModel.authorize(state.email, state.password, navigator)},
                            text = VestaResourceStrings.sign_in
                        )
                        Spacer(Modifier.height(25.dp))
                        CustomSplitClickableText(
                            text = VestaResourceStrings.wanna_registration,
                            onClick = {navigator.push(SignUpScreen())}
                        )
                    }
                }
            }
        ) {
            Box(Modifier.fillMaxSize()){
                Column(Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 20.dp, horizontal = 20.dp)) {

                    RoundedTextField(
                        value = state.email,
                        onValueChange = {viewModel.updateEmail(it)},
                        placeholder = VestaResourceStrings.email,
                        errorMessage = state.errorEmail
                    )
                    Spacer(Modifier.height(20.dp))
                    RoundedTextField(
                        value = state.password,
                        onValueChange = {viewModel.updatePassword(it)},
                        placeholder = VestaResourceStrings.password,
                        errorMessage = state.errorPassword,
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Text(
                        text = VestaResourceStrings.forgot_password,

                        color =  MaterialTheme.colorScheme.primary,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        letterSpacing = 0.5.sp,
                        lineHeight = 14.63.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 15.dp).clickable{}
                    )
                }
            }
        }
    }
}