package com.example.mymvvm.service

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllCarData() = apiService.getAllCar()
}