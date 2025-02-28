package com.ucne.adielgarcia_p2_p2.data.repository

import com.ucne.adielgarcia_p2_p2.data.remote.RemoteDataSource
import com.ucne.adielgarcia_p2_p2.data.remote.Resource
import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DepositosRepository @Inject constructor(
    private val dataSource: RemoteDataSource
) {
    fun getDepositos(): Flow<Resource<List<DepositoDto>>> = flow {
        try {
            emit(Resource.Loading())

            val result = dataSource.getDepositos()

            emit(Resource.Success(result))

        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string() ?: e.message()
            emit(Resource.Error("Error desde retro:  $errorMessage"))
        } catch (e: Exception) {
            emit(Resource.Error("Error ${e.message}"))
        }
    }

    fun updateDeposito(deposito: DepositoDto): Flow<Resource<DepositoDto>> = flow {
        try {
            emit(Resource.Loading())

            val result = dataSource.putDeposito(deposito)

            emit(Resource.Success(result))

        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string() ?: e.message()
            emit(Resource.Error("Error desde retro:  $errorMessage"))
        } catch (e: Exception) {
            emit(Resource.Error("Error ${e.message}"))
        }
    }
    fun createDeposito(deposito: DepositoDto): Flow<Resource<DepositoDto>> = flow {
        try {
            emit(Resource.Loading())

            val result = dataSource.postDeposito(deposito)

            emit(Resource.Success(result))

        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string() ?: e.message()
            emit(Resource.Error("Error desde retro:  $errorMessage"))
        } catch (e: Exception) {
            emit(Resource.Error("Error ${e.message}"))
        }
    }
}