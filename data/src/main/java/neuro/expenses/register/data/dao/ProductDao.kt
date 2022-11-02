package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.RoomPricedProduct
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.data.model.bill.*

@Dao
interface ProductDao {
  @Query("select * from product_table where productId=:productId")
  fun getProduct(productId: Long): Maybe<RoomProduct>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(roomProduct: RoomProduct): Single<Long>

  @Update()
  fun update(roomProduct: RoomProduct): Completable

  @Delete()
  fun delete(roomProduct: RoomProduct): Completable

  @Query("select * from priced_product_table where pricedProductId=:pricedProductId")
  fun getPricedProduct(pricedProductId: Long): Single<RoomPricedProduct>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(roomPricedProduct: RoomPricedProduct): Single<Long>

  @Transaction
  fun insert(roomProduct: RoomProduct, category: String, price: Double): Long {
    return insert(roomProduct).flatMap { productId ->
      insert(
        RoomPricedProduct(productId, category, price)
      ).flatMap { pricedProductId ->
        insert(
          PricedProductCategoryCrossRef(
            pricedProductId,
            category
          )
        ).concatMap { insert(PricedProductProductCrossRef(pricedProductId, productId)) }
          .map { pricedProductId }
      }
    }.blockingGet()
  }

  @Delete()
  fun delete(roomPricedProduct: RoomPricedProduct): Completable

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(productCategoryCrossRef: PricedProductCategoryCrossRef): Single<Long>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(pricedProductProductCrossRef: PricedProductProductCrossRef): Single<Long>
}
