package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class RoomProduct(
  val description: String,
  val iconUrl: String = "",
  @PrimaryKey(autoGenerate = true)
  var productId: Long = 0
)