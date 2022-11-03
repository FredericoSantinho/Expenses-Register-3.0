package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import neuro.expenses.register.data.model.RoomPricedProduct

@Entity(
  tableName = "bill_item_table", foreignKeys = [ForeignKey(
    entity = RoomPricedProduct::class,
    parentColumns = arrayOf("pricedProductId"),
    childColumns = arrayOf("pricedProductId"),
    onDelete = ForeignKey.CASCADE
  )], indices = [Index(value = ["pricedProductId"])]
)
data class RoomBillItem(
  @PrimaryKey(autoGenerate = true)
  val billItemId: Long = 0,
  val amount: Double,
  val pricedProductId: Long,
  val parentBillId: Long
)