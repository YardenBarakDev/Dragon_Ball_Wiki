package com.rentx.core.presentation

//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.lifecycle.ViewModel
//import androidx.navigation.NavBackStackEntry
//import androidx.navigation.NavController
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
//    navController: NavController
//): T {
//    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//    return koinViewModel(
//        viewModelStoreOwner = parentEntry
//    )
//}