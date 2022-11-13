package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place_table")
data class RoomPlace(
  val name: String,
  val latLng: LatLng,
  @PrimaryKey
  val placeId: String
)