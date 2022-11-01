package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["pricedProductId", "productId"], indices = [Index(value = ["productId"])])
data class PricedProductProductCrossRef(
    val pricedProductId: Long,
    val productId: Long
)
