package com.manish.teachmintassignment.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.manish.teachmintassignment.presentation.NavArgs.REPO_ITEM
import com.manish.teachmintassignment.presentation.NavArgs.REPO_OWNER
import com.manish.teachmintassignment.presentation.NavArgs.USER_NAME
import com.manish.teachmintassignment.presentation.Routes.HOME_SCREEN
import com.manish.teachmintassignment.presentation.Routes.REPO_DETAILS_SCREEN
import com.manish.teachmintassignment.presentation.Routes.USER_REPO_LIST_SCREEN
import com.manish.teachmintassignment.presentation.screens.homeScreen.HomeScreen
import com.manish.teachmintassignment.presentation.screens.repoDetails.RepoDetailsScreen
import com.manish.teachmintassignment.presentation.screens.userRepoList.UserRepoListScreen

object Routes {
    const val HOME_SCREEN = "homeScreen"
    const val REPO_DETAILS_SCREEN = "repoDetailsScreen"
    const val USER_REPO_LIST_SCREEN = "userRepoListScreen"
}

object NavArgs{
    const val REPO_OWNER = "repoOwner"
    const val REPO_ITEM = "repoItem"
    const val USER_NAME = "userName"
}

@Composable
fun TeachMintNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(HOME_SCREEN) {
            HomeScreen(navController)
        }

        composable(REPO_DETAILS_SCREEN.plus("/{${REPO_OWNER}}/{${REPO_ITEM}}"), arguments = listOf(
            navArgument(REPO_ITEM) {
                type = NavType.StringType
            },
        )) {
            RepoDetailsScreen(navController, gitRepoOwner = it.arguments?.getString(REPO_OWNER) ?: "", gitRepoItem = it.arguments?.getString(REPO_ITEM) ?: "")
        }

        composable(USER_REPO_LIST_SCREEN.plus("/{${USER_NAME}}"), arguments = listOf(
            navArgument(USER_NAME) {
                type = NavType.StringType
            },
        )) {
            UserRepoListScreen(navController, userName = it.arguments?.getString(USER_NAME) ?: "",)
        }
    }
}