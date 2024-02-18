package com.cvs.code.challenge.ui.screen.imagesearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cvs.code.challenge.R
import com.cvs.code.challenge.domain.model.images.Item
import com.cvs.code.challenge.domain.model.images.Media
import com.cvs.code.challenge.ui.screen.imagesearch.custom_arguments.ImageDetails
import com.cvs.code.challenge.ui.screen.imagesearch.searchbar.MySearchAppBar
import com.cvs.code.challenge.ui.state.ImageSearchState
import com.cvs.code.challenge.ui.viewmodel.ImagesViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ImageSearchListScreen(
    imagesViewModel: ImagesViewModel,
    navigateToDetailsScreen: (ImageDetails) -> Unit
) {

    val screenStateUI by imagesViewModel.uiState.collectAsState()

    var searchText by remember { mutableStateOf("Cars") }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)){
            MySearchAppBar(
                query = searchText,
                onQueryChanged = {
                    searchText = it
                    imagesViewModel.getImageSearch(searchText)
                },
                onExecuteSearch = {},
            )

            when(screenStateUI) {

                is ImageSearchState.Loading -> {
                    HorizontalDottedProgressBar()
                }

                is ImageSearchState.SuccessState -> {
                    ImageSearchList(
                        imageList = (screenStateUI as ImageSearchState.SuccessState).imagesSearch.items,
                        navigateToDetailsScreen = navigateToDetailsScreen
                    )
                }

                is ImageSearchState.ErrorState -> {
                    ErrorScreen()
                }
            }
        }
    }
}

@Composable
fun ImageSearchList(
    imageList: List<Item>,
    navigateToDetailsScreen: (ImageDetails) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(imageList.size) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(imageList[it].media.m),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clickable {
                            navigateToDetailsScreen(
                                ImageDetails(imageList[it].media.m,
                                    imageList[it].title,
                                    imageList[it].description,
                                    imageList[it].author,
                                    imageList[it].date_taken)
                            )
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageSearchListPreview(){
    val items = mutableListOf<Item>()
    items.add(Item("","","","","", Media(""),"","",""))
    items.add(Item("","","","","", Media(""),"","",""))
    items.add(Item("","","","","", Media(""),"","",""))
    ImageSearchList(imageList = items, navigateToDetailsScreen = {})
}