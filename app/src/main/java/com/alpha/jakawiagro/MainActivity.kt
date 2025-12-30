package com.alpha.jakawiagro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alpha.jakawiagro.layout.AppRoot
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JakawiAgroTheme {
                AppRoot()
            }
        }
    }
}



