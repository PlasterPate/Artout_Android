package com.mbglobal.data.entity.event

data class S3ResponseEntity (
    val url: String,
    val key: Int,
    val awsAccessKeyId: String,
    val policy: String,
    val signature: String
)