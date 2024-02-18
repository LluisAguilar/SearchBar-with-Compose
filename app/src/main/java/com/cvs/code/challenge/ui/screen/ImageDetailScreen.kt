package com.cvs.code.challenge.ui.screen

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.rememberAsyncImagePainter
import com.cvs.code.challenge.R
import com.cvs.code.challenge.ui.screen.imagesearch.custom_arguments.ImageDetails
import com.cvs.code.challenge.ui.theme.ImageSearchTheme
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(
    navigateBack: () -> Unit,
    params: ImageDetails?
    ) {

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
                        stringResource(R.string.details),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack.invoke() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

            Image(
                painter = rememberAsyncImagePainter(params?.imageLink),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )

            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(text = stringResource(R.string.title))
                params?.let {
                    Text(text = it.title)
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                )

                Text(text = stringResource(R.string.description))
                params?.description?.let {
                    HtmlText(html = it)
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                )

                Text(text = stringResource(R.string.author))
                params?.let {
                    Text(text = it.author)
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                )

                Text(text = stringResource(R.string.date))
                params?.let {
                    getDateTimeForDetails(it.date)?.let {
                        Text(text = it)
                    }
                }
            }
        }
    }
}

fun getDateTimeForDetails(s: String): String? {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("dd-MMMM-yyyy",Locale.getDefault())
    val outpuDate = formatter.format(parser.parse(s.substring(0,s.length-4)))
    return outpuDate
}

@Composable
fun HtmlText(html: String) {
    AndroidView(
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Preview(showBackground = true)
@Composable
fun ImageDetailPreview() {
    ImageSearchTheme {
        ImageDetailScreen(navigateBack = {}, ImageDetails("","","","",""))
    }
}