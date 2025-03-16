package com.rentx.dragonballwiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rentx.dragonballwiki.presentation.home_page.DragonBallHomePage
import com.rentx.dragonballwiki.ui.theme.DragonBallWikiTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DragonBallWikiTheme {
                DragonBallHomePage()

            }
        }
    }
}
