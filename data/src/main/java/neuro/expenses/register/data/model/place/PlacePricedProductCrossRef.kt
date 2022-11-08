package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["name", "pricedProductId"], indices = [Index(value = ["pricedProductId"])])
data class PlacePricedProductCrossRef(
  val name: String,
  val pricedProductId: Long
)
