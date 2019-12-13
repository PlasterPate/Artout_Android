package com.mbglobal.local

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.net.toUri
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class FileCreator {
    companion object {
        private const val NAME_PREFIX = "IMG_"
        private const val TYPE_JPEG = ".jpeg"

        fun saveImageFile(imageUri: Uri?, context: Context): String {

            val path = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                generateName()
            )
            imageUri?.let {uri ->
                val imageByteArray =
                    context.contentResolver.openInputStream(uri)
                        ?.readBytes()

                path.outputStream().use {
                    it.write(imageByteArray!!)
                }
            }
            return path.toUri().toString()
        }

        private fun generateName(): String {
            val currentDate = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(
                Calendar.getInstance().time)
            return NAME_PREFIX.plus(currentDate).plus(TYPE_JPEG)
        }
    }
}