package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class RoomCategory(
  @PrimaryKey
  val name: String
)