package neuro.expenses.register.data.model.product

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["placeProductId", "name"], indices = [Index(value = ["name"])])
data class PlaceProductCategoryCrossRef(
  val placeProductId: Long,
  val name: String
)
