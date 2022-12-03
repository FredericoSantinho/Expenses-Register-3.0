package neuro.expenses.register.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import neuro.expenses.register.data.converter.Converters
import neuro.expenses.register.data.dao.*
import neuro.expenses.register.data.model.bill.BillItemPlaceProductCrossRef
import neuro.expenses.register.data.model.bill.BillPlaceCrossRef
import neuro.expenses.register.data.model.bill.RoomBill
import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.data.model.category.RoomCategory
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.data.model.placeproduct.PlaceProductCategoryCrossRef
import neuro.expenses.register.data.model.placeproduct.PlaceProductProductCrossRef
import neuro.expenses.register.data.model.placeproduct.RoomPlaceProduct
import neuro.expenses.register.data.model.product.RoomProduct

@Database(
  entities = [RoomProduct::class, RoomPlaceProduct::class, RoomCategory::class, RoomBill::class,
    RoomBillItem::class, BillItemPlaceProductCrossRef::class, BillPlaceCrossRef::class, PlaceProductCategoryCrossRef::class,
    PlaceProductProductCrossRef::class, RoomPlace::class, PlacePlaceProductCrossRef::class],
  version = 1
)
@TypeConverters(Converters::class)
abstract class ExpensesRegisterDatabase : RoomDatabase() {
  abstract val categoryDao: CategoryDao
  abstract val billDao: BillDao
  abstract val productDao: ProductDao
  abstract val placeProductDao: PlaceProductDao
  abstract val placeDao: PlaceDao
}
