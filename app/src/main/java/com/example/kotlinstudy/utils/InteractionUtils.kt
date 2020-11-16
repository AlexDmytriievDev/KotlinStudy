package com.example.kotlinstudy.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import android.webkit.URLUtil
import android.widget.Toast
import com.example.kotlinstudy.utils.Constants.INTERACTION.HTTPS_URI
import com.example.kotlinstudy.utils.Constants.INTERACTION.HTTP_URI
import com.example.kotlinstudy.utils.Constants.INTERACTION.PACKAGE

object InteractionUtils {

    fun openUrl(context: Context, url: String?) {
        val safeUrl = getSafeUrl(url)
        if (TextUtils.isEmpty(safeUrl)) {
            Toast.makeText(context, "invalid URL", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }

    private fun getSafeUrl(url: String?): String {
        if (!url.isNullOrEmpty()) {
            if (URLUtil.isValidUrl(url)) return url
            else if (!url.startsWith(HTTP_URI) || !url.startsWith(HTTPS_URI)) return HTTP_URI + url
        }
        return Constants.CHAR.EMPTY
    }

    fun openSettings(context: Context): Intent? {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts(PACKAGE, context.packageName, null)
        return intent
    }
}