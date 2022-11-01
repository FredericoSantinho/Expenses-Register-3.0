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
    onDelete = ForeignKey.RESTRICT
  )], indices = [Index(value = ["pricedProductId"])]
)
data class RoomBillItem(
  val amount: Double,
  val pricedProductId: Long,
  @PrimaryKey(autoGenerate = true)
  var billItemId: Long = 0,
)