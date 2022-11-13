package neuro.expenses.register.data.model.product

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["placeProductId", "categoryId"], indices = [Index(value = ["categoryId"])])
data class PlaceProductCategoryCrossRef(
  val placeProductId: Long,
  val categoryId: String
)
