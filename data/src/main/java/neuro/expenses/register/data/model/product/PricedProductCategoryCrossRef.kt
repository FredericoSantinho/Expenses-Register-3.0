package neuro.expenses.register.data.model.product

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["pricedProductId", "name"], indices = [Index(value = ["name"])])
data class PricedProductCategoryCrossRef(
  val pricedProductId: Long,
  val name: String
)
