package com.wynsel.landcritter.http

import com.wynsel.landcritter.http.models.Status
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CallService {

    @GET("directions/json")
    fun search(@QueryMap params : Map<String, String>) : Flowable<Status<String>>

}