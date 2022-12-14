package neuro.expenses.register.data.model.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "category_table", indices = [Index(value = ["nameLowercase"], unique = true)])
data class RoomCategory(
  @PrimaryKey
  val categoryId: Long,
  val name: String,
  val iconUrl: String,
  val nameLowercase: String = name.lowercase()
)