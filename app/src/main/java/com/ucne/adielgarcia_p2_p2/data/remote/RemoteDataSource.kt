package com.ucne.adielgarcia_p2_p2.data.remote

import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: SagApi
){
    suspend fun getDepositos() = api.getDepositos()

    suspend fun postDeposito(depositoDto: DepositoDto)= api.postDeposito(depositoDto)

    suspend fun  putDeposito(deposito: DepositoDto) = api.putDeposito(deposito = deposito, id = deposito.idDeposito)
}