package com.rentx.dragonballwiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rentx.dragonballwiki.navigation.Route
import com.rentx.dragonballwiki.presentation.character_page.CharacterPage
import com.rentx.dragonballwiki.presentation.components.MainHeader
import com.rentx.dragonballwiki.presentation.home_page.DragonBallHomePage
import com.rentx.dragonballwiki.presentation.home_page.HomePageVM
import com.rentx.dragonballwiki.presentation.components.SelectedCharacterVM
import com.rentx.dragonballwiki.ui.theme.DragonBallWikiTheme
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DragonBallWikiTheme {
                val selectedCharacterVM = koinViewModel<SelectedCharacterVM>()
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MainHeader()
                    },
                     content = { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.DragonBallHomePage
                        ) {
                            composable<Route.DragonBallHomePage> {
                                val viewModel = koinViewModel<HomePageVM>()
                                LaunchedEffect(true) {
                                    selectedCharacterVM.onSelectedCharacter(null)
                                }
                                DragonBallHomePage(Modifier.padding(padding), viewModel, selectedCharacterVM) { character ->
                                    selectedCharacterVM.onSelectedCharacter(character)
                                    navController.navigate(Route.CharacterDetail)
                                }
                            }
                            composable<Route.CharacterDetail> {
                                CharacterPage(Modifier.padding(padding), selectedCharacterVM) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                )

            }
        }
    }
}
