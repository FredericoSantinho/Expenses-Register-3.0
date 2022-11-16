package neuro.expenses.register.entity

data class Place(
  val id: Long,
  val name: String,
  val placeProducts: List<PlaceProduct>,
  val latLng: LatLng
)