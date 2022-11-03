package neuro.expenses.register.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import neuro.expenses.register.data.converter.Converters
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.RoomPricedProduct
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.data.model.bill.*

@Database(
  entities = [RoomProduct::class, RoomPricedProduct::class, RoomCategory::class, RoomBill::class,
    RoomBillItem::class, BillItemPricedProductCrossRef::class, PricedProductCategoryCrossRef::class,
    PricedProductProductCrossRef::class],
  version = 1
)
@TypeConverters(Converters::class)
abstract class ExpensesRegisterDatabase : RoomDatabase() {
  abstract val categoryDao: CategoryDao
  abstract val billDao: BillDao
  abstract val productDao: ProductDao
}
