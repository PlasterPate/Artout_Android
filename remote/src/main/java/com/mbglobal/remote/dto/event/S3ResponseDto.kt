package com.mbglobal.remote.dto.event

import com.google.gson.annotations.SerializedName

data class S3ResponseDto (
    @SerializedName("url")
    val url: String,
    @SerializedName("fields")
    val s3FieldsDto: S3FieldsDto
)