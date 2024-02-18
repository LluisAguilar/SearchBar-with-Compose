package com.cvs.code.challenge.ui.navigation

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cvs.code.challenge.ui.navigation.ImageSearchDestinations.IMAGE_SEARCH_DETAILS
import com.cvs.code.challenge.ui.navigation.ImageSearchDestinations.IMAGE_SEARCH_DETAILS_ARGS
import com.cvs.code.challenge.ui.navigation.ImageSearchDestinations.IMAGE_SEARCH_LIST
import com.cvs.code.challenge.ui.screen.ImageDetailScreen
import com.cvs.code.challenge.ui.screen.imagesearch.ImageSearchListScreen
import com.cvs.code.challenge.ui.screen.imagesearch.custom_arguments.ImageDetails
import com.cvs.code.challenge.ui.screen.imagesearch.custom_arguments.ImageParamType
import com.cvs.code.challenge.ui.viewmodel.ImagesViewModel
import com.google.gson.Gson


@Composable
fun ImageSearchNavGraph(
    navGraph: NavHostController,
    imagesViewModel: ImagesViewModel,
    startDestination: String,
    navigateBack: ()-> Unit,
){
    NavHost(
        navController = navGraph,
        startDestination = startDestination
    ) {

        composable(route = IMAGE_SEARCH_LIST) {
            BackHandler {
                // TODO: nav back or finish
            }

            ImageSearchListScreen(
                imagesViewModel = imagesViewModel
            ) {
                val json = Uri.encode(Gson().toJson(it))
                navGraph.navigate("$IMAGE_SEARCH_DETAILS/$json")
            }
        }

        composable(
            route = "$IMAGE_SEARCH_DETAILS/{$IMAGE_SEARCH_DETAILS_ARGS}",
            arguments = listOf(
                navArgument(IMAGE_SEARCH_DETAILS_ARGS) {
                    type = ImageParamType()
                })
        ) { backStackEntry ->

            val arguments = requireNotNull(backStackEntry.arguments)
            val details = arguments.getParcelable<ImageDetails>(IMAGE_SEARCH_DETAILS_ARGS)

            BackHandler {
                // TODO: nav back or finish
            }
            ImageDetailScreen(
                navigateBack = navigateBack,
                params = details
            )
        }

    }

}

object ImageSearchDestinations {
    const val IMAGE_SEARCH_LIST = "image_search_list"
    const val IMAGE_SEARCH_DETAILS = "image_search_details"
    const val IMAGE_SEARCH_DETAILS_ARGS = "image_search_details_args"
}