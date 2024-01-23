package me.ash.reader.ui.page.home.flow

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import me.ash.reader.ui.ext.collectAsStateValue
import me.ash.reader.ui.page.home.HomeViewModel
import me.ash.reader.ui.page.home.reading.ReadingPage

enum class FlowScreenType {
    FlowWithArticleDetails,
    Flow,
    ArticleDetails,
}

fun getFlowScreenType(isExpanded: Boolean, flowUiState: FlowUiState): FlowScreenType = when (isExpanded) {
    true -> {
        FlowScreenType.FlowWithArticleDetails
    }
    false -> {
        when (flowUiState.isArticleOpen) {
            true -> FlowScreenType.ArticleDetails
            false -> FlowScreenType.Flow
        }
    }
}

@Composable
fun FlowRoute(
    navController: NavHostController,
    isExpandedScreen: Boolean,
    homeViewModel: HomeViewModel,
    flowViewModel: FlowViewModel = hiltViewModel(),
) {
    val flowState = flowViewModel.flowUiState.collectAsStateValue()

    val flowScreenType = getFlowScreenType(isExpandedScreen, flowState)

    when (flowScreenType) {
        FlowScreenType.FlowWithArticleDetails -> {
            FlowWithArticleDetailsScreen(
                navController = navController,
                isExpandedScreen = isExpandedScreen,
                homeViewModel = homeViewModel,
                flowViewModel = flowViewModel,
            )
        }
        FlowScreenType.Flow -> {
            FlowPage(
                navController = navController,
                isExpandedScreen = isExpandedScreen,
                homeViewModel = homeViewModel,
                flowViewModel = flowViewModel,
            )
        }
        FlowScreenType.ArticleDetails -> {
            ReadingPage(
                navController = navController,
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
            )
        }
    }
}