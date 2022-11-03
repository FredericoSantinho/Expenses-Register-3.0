package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "priced_product_table",
  indices = [Index(
    value = ["productId", "category", "price"],
    unique = true
  ), Index(value = ["category"])],
  foreignKeys = [ForeignKey(
    entity = RoomProduct::class,
    parentColumns = arrayOf("productId"),
    childColumns = arrayOf("productId"),
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = RoomCategory::class,
    parentColumns = arrayOf("name"),
    childColumns = arrayOf("category"),
    onDelete = ForeignKey.CASCADE
  )]
)
data class RoomPricedProduct(
  val productId: Long,
  val category: String,
  val price: Double,
  @PrimaryKey(autoGenerate = true)
  var pricedProductId: Long = 0
)
