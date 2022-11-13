package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class RoomCategory(
  val name: String,
  @PrimaryKey
  val categoryId: String = name.lowercase()
)