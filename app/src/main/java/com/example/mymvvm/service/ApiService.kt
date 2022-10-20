package com.example.mymvvm.service

import com.example.mymvvm.model.GetAllCarResponse
import retrofit2.http.GET

interface ApiService {
    @GET("admin/car")
    suspend fun getAllCar(): GetAllCarResponse
}