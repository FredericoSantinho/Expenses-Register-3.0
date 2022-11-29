package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "place_table",
  indices = [Index(value = ["nameLowercase"], unique = true)],
  foreignKeys = [ForeignKey(
    entity = RoomPlace::class,
    parentColumns = arrayOf("placeId"),
    childColumns = arrayOf("placeId"),
    onDelete = ForeignKey.NO_ACTION
  )]
)
data class RoomPlace(
  @PrimaryKey
  val placeId: Long,
  val name: String,
  val latLngModel: LatLngModel,
  val nameLowercase: String = name.lowercase()
)