package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "place_table", indices = [Index(value = ["nameLowercase"], unique = true)])
data class RoomPlace(
  @PrimaryKey(autoGenerate = true)
  val placeId: Long,
  val name: String,
  val latLngModel: LatLngModel,
  val nameLowercase: String = name.lowercase()
)