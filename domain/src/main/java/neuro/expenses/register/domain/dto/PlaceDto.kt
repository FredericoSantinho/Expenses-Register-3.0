package neuro.expenses.register.domain.dto

data class PlaceDto(val name: String, val products: List<ProductDto>, val latLngDto: LatLngDto)