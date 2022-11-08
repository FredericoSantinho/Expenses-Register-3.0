package neuro.expenses.register.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import neuro.expenses.register.data.converter.Converters
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.RoomPricedProduct
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.data.model.bill.BillItemPricedProductCrossRef
import neuro.expenses.register.data.model.bill.RoomBill
import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.data.model.place.PlacePricedProductCrossRef
import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.data.model.product.PricedProductCategoryCrossRef
import neuro.expenses.register.data.model.product.PricedProductProductCrossRef

@Database(
  entities = [RoomProduct::class, RoomPricedProduct::class, RoomCategory::class, RoomBill::class,
    RoomBillItem::class, BillItemPricedProductCrossRef::class, PricedProductCategoryCrossRef::class,
    PricedProductProductCrossRef::class, RoomPlace::class, PlacePricedProductCrossRef::class],
  version = 1
)
@TypeConverters(Converters::class)
abstract class ExpensesRegisterDatabase : RoomDatabase() {
  abstract val categoryDao: CategoryDao
  abstract val billDao: BillDao
  abstract val productDao: ProductDao
  abstract val placeDao: PlaceDao
}
