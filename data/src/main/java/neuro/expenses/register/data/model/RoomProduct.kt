package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "product_table", indices = [Index(value = ["description"], unique = true)])
data class RoomProduct(
  @PrimaryKey(autoGenerate = true)
  var productId: Long,
  val description: String,
  val iconUrl: String = ""
)