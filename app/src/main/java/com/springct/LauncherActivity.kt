package com.springct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.springct.ui.compose.AppNavigation

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UI()
        }
    }

    @Composable
    private fun UI(
        modifier: Modifier = Modifier
    ) {
        AppNavigation(
            closeApp = {
                finish()
            },
            modifier = modifier
        )
    }

    @Preview
    @Composable
    private fun UIPreview() {
        AppNavigation(
            closeApp = {
                finish()
            }
        )
    }
}