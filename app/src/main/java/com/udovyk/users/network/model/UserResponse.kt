package com.udovyk.users.network.model

data class UserResponse(
	val results: List<ResultsItem?>? = null,
	val info: Info? = null
)
