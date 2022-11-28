package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.product.PlaceProductCategoryCrossRef
import neuro.expenses.register.data.model.product.PlaceProductProductCrossRef
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory

@Dao
interface PlaceProductDao {
  @Query("SELECT MAX(placeProductId) FROM place_product_table")
  fun getLastPlaceProductId(): Maybe<Long>

  @Transaction
  @Query("select * from place_product_table where placeProductId=:placeProductId")
  fun getPlaceProduct(placeProductId: Long): Maybe<RoomPlaceProductWithProductAndCategory>

  @Query("select * from place_product_table where productId=:productId and categoryId=:categoryId and price=:price")
  fun getPlaceProduct(productId: Long, categoryId: Long, price: Double): Maybe<RoomPlaceProduct>

  @Transaction
  @Query("select * from place_product_table where productId=:productId and categoryId=:categoryId and price=:price")
  fun getPlaceProductWithProductAndCategory(
    productId: Long, categoryId: Long, price: Double
  ): Maybe<RoomPlaceProductWithProductAndCategory>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(roomPlaceProduct: RoomPlaceProduct): Long

  @Transaction
  fun insert(
    roomPlaceProduct: RoomPlaceProduct,
    placeProductProductCrossRef: PlaceProductProductCrossRef,
    placeProductCategoryCrossRef: PlaceProductCategoryCrossRef
  ) {
    insert(roomPlaceProduct)
    insert(placeProductProductCrossRef)
    insert(placeProductCategoryCrossRef)
  }

  @Delete()
  fun delete(roomPlaceProduct: RoomPlaceProduct): Completable

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(productCategoryCrossRef: PlaceProductCategoryCrossRef): Long

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(placeProductProductCrossRef: PlaceProductProductCrossRef): Long
}
