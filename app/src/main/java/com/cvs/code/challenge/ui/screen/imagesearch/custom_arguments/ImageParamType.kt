package com.cvs.code.challenge.ui.screen.imagesearch.custom_arguments

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class ImageParamType : NavType<ImageDetails>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ImageDetails? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ImageDetails {
        return Gson().fromJson(value, ImageDetails::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ImageDetails) {
        bundle.putParcelable(key, value)
    }

}