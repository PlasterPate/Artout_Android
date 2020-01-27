package com.mbglobal.data.entity.event

data class AddEventResponseEntity (
    val event: EventEntity,
    val s3ResponseEntity: S3ResponseEntity
)