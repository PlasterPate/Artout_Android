package com.mbglobal.remote.api

import com.mbglobal.remote.dto.event.S3FieldsDto
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface S3Service {

    @Multipart
    @Headers("Content-Type:image/jpeg")
    @POST("{url}")
    fun uploadImage(@Path("url") url: String,
                    @Part("fields") fields: S3FieldsDto,
                    @Part image: MultipartBody.Part
    ):
            Single<ResponseBody>
}