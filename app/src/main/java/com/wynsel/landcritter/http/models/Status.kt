package com.wynsel.landcritter.http.models

import com.google.gson.annotations.SerializedName

data class Status<T>(
        @SerializedName("status") val status : Int,
        @SerializedName("message") val message : String,
        @SerializedName("result") val result: T
)