package com.uharris.sakeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.uharris.sakeapp.ui.navigation.App
import com.uharris.sakeapp.ui.theme.SakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SakeAppTheme {
                App()
            }
        }
    }
}