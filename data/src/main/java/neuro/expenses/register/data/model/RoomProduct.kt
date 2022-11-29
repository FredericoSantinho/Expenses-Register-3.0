package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "product_table",
  indices = [Index(value = ["descriptionLowercase"], unique = true)]
)
data class RoomProduct(
  @PrimaryKey
  var productId: Long,
  val description: String,
  val iconUrl: String,
  val variableAmount: Boolean,
  val descriptionLowercase: String = description.lowercase()
)