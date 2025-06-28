package com.uharris.sakeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.uharris.sakeapp.detail.ui.DetailUi
import com.uharris.sakeapp.main.ui.MainUi
import kotlinx.serialization.Serializable

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Main) {
        composable<Routes.Main> {
            MainUi(
                onShopClick = { index ->
                    navController.navigateToDetail(index)
                }
            )
        }
        composable<Routes.Detail> { backStackEntry ->
            val detail: Routes.Detail = backStackEntry.toRoute()
            DetailUi(index = detail.index, onBack = { navController.popBackStack() })
        }
    }
}

fun NavHostController.navigateToDetail(index: Int) {
    navigate(Routes.Detail(index = index))
}

object Routes {
    @Serializable
    object Main

    @Serializable
    data class Detail(val index: Int)
}