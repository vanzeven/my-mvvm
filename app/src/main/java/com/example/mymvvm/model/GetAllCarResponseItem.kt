package com.example.mymvvm.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAllCarResponseItem(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("finish_rent_at")
    val finishRentAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("start_rent_at")
    val startRentAt: String? = null,
    @SerializedName("status")
    val status: Boolean? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
) : Parcelable