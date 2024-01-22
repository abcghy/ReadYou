package me.ash.reader.ui.page.home.flow

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import me.ash.reader.ui.page.home.HomeViewModel

enum class FlowScreenType {
    FlowWithArticleDetails,
    Flow,
}

fun getFlowScreenType(isExpanded: Boolean): FlowScreenType = when (isExpanded) {
    true -> {
        FlowScreenType.FlowWithArticleDetails
    }
    false -> {
        FlowScreenType.Flow
    }
}

@Composable
fun FlowRoute(
    navController: NavHostController,
    isExpandedScreen: Boolean,
    homeViewModel: HomeViewModel,
) {
    val flowScreenType = getFlowScreenType(isExpandedScreen)
    when (flowScreenType) {
        FlowScreenType.FlowWithArticleDetails -> {
            FlowWithArticleDetailsScreen(
                navController = navController,
                isExpandedScreen = isExpandedScreen,
                homeViewModel = homeViewModel
            )
        }
        FlowScreenType.Flow -> {
            FlowPage(
                navController = navController,
                isExpandedScreen = isExpandedScreen,
                homeViewModel = homeViewModel
            )
        }
    }
}