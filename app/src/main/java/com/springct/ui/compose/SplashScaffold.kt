package com.springct.ui.compose

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.springct.R
import com.springct.ui.view_model.SplashViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScaffold(
    navigateToNextScreen: (isUserLoggedIn: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel = getViewModel()
) {

    LaunchedEffect(keys = emptyArray()) {
        Handler(Looper.getMainLooper())
            .postDelayed({
                splashViewModel.checkLoginStatus()
            }, 2500)
    }

    splashViewModel.isLoggedIn.value?.let { isLoggedIn ->
        splashViewModel.isLoggedIn.value = null
        navigateToNextScreen(isLoggedIn)
    }

    Box(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(size = 300.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
    }

}