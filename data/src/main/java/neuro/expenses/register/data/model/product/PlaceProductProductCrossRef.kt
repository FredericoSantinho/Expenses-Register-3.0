package neuro.expenses.register.data.model.product

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["placeProductId", "productId"], indices = [Index(value = ["productId"])])
data class PlaceProductProductCrossRef(
  val placeProductId: Long,
  val productId: Long
)
