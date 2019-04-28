package com.udovyk.users.network.model

data class Location(
	val city: String? = null,
	val street: String? = null,
	val timezone: Timezone? = null,
	val postcode: String? = null,
	val coordinates: Coordinates? = null,
	val state: String? = null
)
