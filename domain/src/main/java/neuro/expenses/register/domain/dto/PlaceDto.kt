package neuro.expenses.register.domain.dto

data class PlaceDto(val name: String, val latLng: LatLngDto, val products: List<ProductDto>)