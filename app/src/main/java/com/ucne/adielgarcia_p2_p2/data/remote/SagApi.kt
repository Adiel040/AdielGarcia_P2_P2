package com.ucne.adielgarcia_p2_p2.data.remote

import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SagApi {
    @Headers("X-API-Key:test")
    @GET("api/Depositos")
    suspend fun getDepositos(): List<DepositoDto>

    @Headers("X-API-Key:test")
    @POST("api/Depositos")
    suspend fun postDeposito(@Body deposito: DepositoDto): DepositoDto

    @Headers("X-API-Key:test")
    @PUT("api/Depositos/{id}")
    suspend fun putDeposito(@Path("id") id: Int, @Body deposito: DepositoDto): DepositoDto
}