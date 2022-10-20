package com.example.mymvvm

import com.example.mymvvm.service.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getAllCarData()
}