package com.ucne.adielgarcia_p2_p2.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.ucne.adielgarcia_p2_p2.data.remote.dto.DepositoDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class DepositoNavType : NavType<DepositoDto?>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): DepositoDto? {
        return bundle.getString(key)?.let {
            Json.decodeFromString<DepositoDto>(it)
        }
    }

    override fun parseValue(value: String): DepositoDto {
        return Json.decodeFromString<DepositoDto>(value)
    }

    override fun put(bundle: Bundle, key: String, value: DepositoDto?) {
        bundle.putString(key, Json.encodeToString(value))
    }
}