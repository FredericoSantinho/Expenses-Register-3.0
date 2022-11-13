package neuro.expenses.register.entity

data class Place(val id: Long, val name: String, val products: List<Product>, val latLng: LatLng)