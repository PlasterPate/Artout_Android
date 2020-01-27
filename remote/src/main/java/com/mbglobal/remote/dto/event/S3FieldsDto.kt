package com.mbglobal.remote.dto.event

import com.google.gson.annotations.SerializedName

data class S3FieldsDto (
    @SerializedName("key")
    val key: Int,
    @SerializedName("AWSAccessKeyId")
    val awsAccessKeyId: String,
    @SerializedName("policy")
    val policy: String,
    @SerializedName("signature")
    val signature: String
)