package neuro.expenses.register.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.converter.Converters
import neuro.expenses.register.data.mocks.*
import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.bill.*
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.data.model.place.RoomPlaceWithPlaceProducts
import neuro.expenses.register.data.model.product.PlaceProductCategoryCrossRef
import neuro.expenses.register.data.model.product.PlaceProductProductCrossRef
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory
import neuro.test.rx.Incrementer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class BillDaoTest {
  private lateinit var billDao: BillDao
  private lateinit var productDao: ProductDao
  private lateinit var placeProductDao: PlaceProductDao
  private lateinit var categoryDao: CategoryDao
  private lateinit var placeDao: PlaceDao
  private lateinit var db: ExpensesRegisterDatabase

  private val incrementer = Incrementer()

  @Before
  fun createDb() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(
      context, ExpensesRegisterDatabase::class.java
    ).addTypeConverter(Converters()).build()
    billDao = db.billDao
    productDao = db.productDao
    placeProductDao = db.placeProductDao
    categoryDao = db.categoryDao
    placeDao = db.placeDao
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun getLastBillId() {
    val roomPlace = roomPlaceMock()
    placeDao.insert(roomPlace).test()

    // We want to make sure repeated calls return the same result
    billDao.getLastBillId().test().assertNoValues().assertNoErrors().assertComplete()
    billDao.getLastBillId().test().assertNoValues().assertNoErrors().assertComplete()
    for (i in 1L..3L) {
      val lastBillId = incrementer.getAndIncrement()
      billDao.insert(roomBillMock(lastBillId, roomPlace.placeId)).test().assertValue(lastBillId)
        .assertNoErrors().assertComplete()
      // We want to make sure repeated calls return the same result
      billDao.getLastBillId().test().assertValue(lastBillId).assertNoErrors().assertComplete()
      billDao.getLastBillId().test().assertValue(lastBillId).assertNoErrors().assertComplete()
    }
  }

  @Test
  @Throws(Exception::class)
  fun getLastBillItemId() {
    val roomCategory = roomCategoryMock()
    val roomProduct = roomProductMock()
    val roomPlace = roomPlaceMock()
    val roomBill = roomBillMock(placeId = roomPlace.placeId)
    categoryDao.insert(roomCategory).test()
    productDao.insert(roomProduct).test()
    placeDao.insert(roomPlace).test()
    billDao.insert(roomBill).test()

    // We want to make sure repeated calls return the same result
    billDao.getLastBillItemId().test().assertNoValues().assertNoErrors().assertComplete()
    billDao.getLastBillItemId().test().assertNoValues().assertNoErrors().assertComplete()
    for (i in 1L..3L) {
      val counter = incrementer.getAndIncrement()
      placeProductDao.insert(
        RoomPlaceProduct(
          counter, roomProduct.productId, roomCategory.categoryId, 1.0 + i
        )
      )
      billDao.insert(RoomBillItem(counter, 1.0, counter, roomBill.billId)).test()
        .assertValue(counter).assertNoErrors().assertComplete()
      // We want to make sure repeated calls return the same result
      billDao.getLastBillItemId().test().assertValue(counter).assertNoErrors().assertComplete()
      billDao.getLastBillItemId().test().assertValue(counter).assertNoErrors().assertComplete()
    }
  }

  @Test
  @Throws(Exception::class)
  fun insertAndGetBill() {
    val roomCategory = roomCategoryMock()
    val roomProduct = roomProductMock()
    val roomPlace = roomPlaceMock()
    val roomPlaceProduct =
      roomPlaceProductMock(productId = roomProduct.productId, categoryId = roomCategory.categoryId)
    val placeProductId = roomPlaceProduct.placeProductId
    val placeId = roomPlace.placeId
    val roomBill = roomBillMock(placeId = roomPlace.placeId)
    val billId = roomBill.billId
    val roomBillItem = roomBillItemMock(placeProductId = placeProductId, billId = billId)
    val billItemId = roomBillItem.billItemId

    categoryDao.insert(roomCategory).test()
    productDao.insert(roomProduct).test()
    placeDao.insert(roomPlace).test()
    placeProductDao.insert(roomPlaceProduct)
    placeDao.insert(PlacePlaceProductCrossRef(placeId, placeProductId)).test()
    placeProductDao.insert(PlaceProductCategoryCrossRef(placeProductId, roomCategory.categoryId))
    placeProductDao.insert(PlaceProductProductCrossRef(placeProductId, roomProduct.productId))
    billDao.insert(roomBill).test().assertValue(roomBill.billId).assertNoErrors().assertComplete()
    billDao.insert(roomBillItem).test().assertValue(roomBillItem.billItemId).assertNoErrors()
      .assertComplete()
    billDao.insert(BillItemPlaceProductCrossRef(billItemId, placeProductId)).test()
    billDao.insert(BillPlaceCrossRef(billId, placeId)).test()

    val roomPlaceProductWithProductAndCategory =
      RoomPlaceProductWithProductAndCategory(roomPlaceProduct, roomProduct, roomCategory)
    val roomBillWithBillItemsAndPlace = RoomBillWithBillItemsAndPlace(
      roomBill,
      listOf(RoomBillItemWithPlaceProduct(roomBillItem, roomPlaceProductWithProductAndCategory)),
      RoomPlaceWithPlaceProducts(roomPlace, listOf(roomPlaceProductWithProductAndCategory))
    )

    billDao.getBill(roomBill.billId).test().assertValue(roomBillWithBillItemsAndPlace)
      .assertNoErrors().assertComplete()
    billDao.getBillItem(roomBillItem.billItemId).test().assertValue(roomBillItem).assertNoErrors()
      .assertComplete()

    // Assert no exceptions on conflict
    billDao.insert(roomBill).test().assertValue(roomBill.billId).assertNoErrors().assertComplete()
    billDao.insert(roomBillItem).test().assertValue(roomBillItem.billItemId).assertNoErrors()
      .assertComplete()
    billDao.insert(BillItemPlaceProductCrossRef(billItemId, placeProductId)).test()
    billDao.insert(BillPlaceCrossRef(billId, placeId)).test()
  }

  @Test
  @Throws(Exception::class)
  fun insertBillWithBillItemsAndGetBill() {
    val roomCategory = roomCategoryMock()
    val roomProduct = roomProductMock()
    val roomPlace = roomPlaceMock()
    val roomPlaceProduct =
      roomPlaceProductMock(productId = roomProduct.productId, categoryId = roomCategory.categoryId)
    val placeProductId = roomPlaceProduct.placeProductId
    val placeId = roomPlace.placeId
    val roomBill = roomBillMock(placeId = roomPlace.placeId)
    val billId = roomBill.billId
    val roomBillItem = roomBillItemMock(placeProductId = placeProductId, billId = billId)
    val billItemId = roomBillItem.billItemId

    categoryDao.insert(roomCategory).test()
    productDao.insert(roomProduct).test()
    placeDao.insert(roomPlace).test()
    placeProductDao.insert(roomPlaceProduct)
    placeDao.insert(PlacePlaceProductCrossRef(placeId, placeProductId)).test().values()
    placeProductDao.insert(PlaceProductCategoryCrossRef(placeProductId, roomCategory.categoryId))
    placeProductDao.insert(PlaceProductProductCrossRef(placeProductId, roomProduct.productId))
    billDao.insert(roomBill, listOf(roomBillItem))

    val roomPlaceProductWithProductAndCategory =
      RoomPlaceProductWithProductAndCategory(roomPlaceProduct, roomProduct, roomCategory)
    val roomBillWithBillItemsAndPlace = RoomBillWithBillItemsAndPlace(
      roomBill,
      listOf(RoomBillItemWithPlaceProduct(roomBillItem, roomPlaceProductWithProductAndCategory)),
      RoomPlaceWithPlaceProducts(roomPlace, listOf(roomPlaceProductWithProductAndCategory))
    )

    billDao.getBill(roomBill.billId).test().assertValue(roomBillWithBillItemsAndPlace)
      .assertNoErrors().assertComplete()
    billDao.getBillItem(roomBillItem.billItemId).test().assertValue(roomBillItem).assertNoErrors()
      .assertComplete()
  }

  @Test
  @Throws(Exception::class)
  fun getLastBill() {
    val roomCategory = roomCategoryMock()
    val roomProduct = roomProductMock()
    val roomPlace = roomPlaceMock()
    val roomPlaceProduct =
      roomPlaceProductMock(productId = roomProduct.productId, categoryId = roomCategory.categoryId)
    val placeProductId = roomPlaceProduct.placeProductId
    val placeId = roomPlace.placeId
    val roomBill = roomBillMock(placeId = roomPlace.placeId)
    val billId = roomBill.billId
    val roomBillItem = roomBillItemMock(placeProductId = placeProductId, billId = billId)

    categoryDao.insert(roomCategory).test()
    productDao.insert(roomProduct).test()
    placeDao.insert(roomPlace).test()
    placeProductDao.insert(roomPlaceProduct)
    placeDao.insert(PlacePlaceProductCrossRef(placeId, placeProductId)).test()
    placeProductDao.insert(PlaceProductCategoryCrossRef(placeProductId, roomCategory.categoryId))
    placeProductDao.insert(PlaceProductProductCrossRef(placeProductId, roomProduct.productId))
    billDao.insert(roomBill, listOf(roomBillItem))

    val roomPlaceProductWithProductAndCategory =
      RoomPlaceProductWithProductAndCategory(roomPlaceProduct, roomProduct, roomCategory)
    val roomBillWithBillItemsAndPlace = RoomBillWithBillItemsAndPlace(
      roomBill,
      listOf(RoomBillItemWithPlaceProduct(roomBillItem, roomPlaceProductWithProductAndCategory)),
      RoomPlaceWithPlaceProducts(roomPlace, listOf(roomPlaceProductWithProductAndCategory))
    )

    billDao.getLastBill().test().assertValue(roomBillWithBillItemsAndPlace).assertNoErrors()
      .assertComplete()
  }
}