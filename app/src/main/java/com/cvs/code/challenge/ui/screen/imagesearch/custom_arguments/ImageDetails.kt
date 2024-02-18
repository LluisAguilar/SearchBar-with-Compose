package com.cvs.code.challenge.ui.screen.imagesearch.custom_arguments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageDetails(
    val imageLink:String,
    val title:String,
    val description:String,
    val author:String,
    val date:String
) : Parcelable
