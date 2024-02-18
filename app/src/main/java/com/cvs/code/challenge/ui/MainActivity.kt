package com.cvs.code.challenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.cvs.code.challenge.ui.navigation.ImageSearchDestinations
import com.cvs.code.challenge.ui.navigation.ImageSearchNavGraph
import com.cvs.code.challenge.ui.theme.ImageSearchTheme
import com.cvs.code.challenge.ui.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var imagesViewModel : ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagesViewModel = ViewModelProvider(this)[ImagesViewModel::class.java]

        setContent {
            ImageSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    ImageSearchNavGraph(
                        navGraph = navController,
                        imagesViewModel = imagesViewModel,
                        startDestination = ImageSearchDestinations.IMAGE_SEARCH_LIST,
                        navigateBack = {
                            navController.navigate(ImageSearchDestinations.IMAGE_SEARCH_LIST)
                        },
                    )

                }
            }
        }
    }

}