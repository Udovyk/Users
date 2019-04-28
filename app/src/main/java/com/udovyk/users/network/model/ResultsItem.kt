package com.udovyk.users.network.model

data class ResultsItem(
	val nat: String? = null,
	val gender: String? = null,
	val phone: String? = null,
	val dob: Dob? = null,
	val name: Name? = null,
	val registered: Registered? = null,
	val location: Location? = null,
	val id: Id? = null,
	val login: Login? = null,
	val cell: String? = null,
	val email: String? = null,
	val picture: Picture? = null
)
