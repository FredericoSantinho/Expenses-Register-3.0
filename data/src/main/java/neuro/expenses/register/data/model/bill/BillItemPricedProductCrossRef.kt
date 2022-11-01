package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["billItemId", "pricedProductId"],
    indices = [Index(value = ["pricedProductId"])]
)
data class BillItemPricedProductCrossRef(
    val billItemId: Long,
    val pricedProductId: Long
)
