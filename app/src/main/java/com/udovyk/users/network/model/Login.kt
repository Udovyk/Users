package com.udovyk.users.network.model

data class Login(
	val sha1: String? = null,
	val password: String? = null,
	val salt: String? = null,
	val sha256: String? = null,
	val uuid: String? = null,
	val username: String? = null,
	val md5: String? = null
)
