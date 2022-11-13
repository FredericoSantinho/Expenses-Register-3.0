package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["name", "placeProductId"], indices = [Index(value = ["placeProductId"])])
data class PlacePlaceProductCrossRef(
  val name: String,
  val placeProductId: Long
)
