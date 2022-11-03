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

  @Query("select * from product_table where description=:description")
  fun getProduct(description: String): Maybe<RoomProduct>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(roomProduct: RoomProduct): Single<Long>

  @Update()
  fun update(roomProduct: RoomProduct): Completable

  @Delete()
  fun delete(roomProduct: RoomProduct): Completable

  @Query("select * from priced_product_table where pricedProductId=:pricedProductId")
  fun getPricedProduct(pricedProductId: Long): Single<RoomPricedProduct>

  @Query("select * from priced_product_table where productId=:productId and category=:category and price=:price")
  fun getPricedProduct(productId: Long, category: String, price: Double): Maybe<RoomPricedProduct>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(roomPricedProduct: RoomPricedProduct): Single<Long>

  @Transaction
  fun insert(category: String, price: Double, description: String): Long {
    return getProduct(description).defaultIfEmpty(RoomProduct(0, description))
      .flatMap { roomProduct ->
        insert(roomProduct).flatMap { productId ->
          getPricedProduct(productId, category, price).defaultIfEmpty(
            RoomPricedProduct(
              productId,
              category,
              price
            )
          ).flatMap { roomPricedProduct ->
            insert(roomPricedProduct)
          }
            .flatMap { pricedProductId ->
              insert(PricedProductCategoryCrossRef(pricedProductId, category))
                .flatMap {
                  insert(PricedProductProductCrossRef(pricedProductId, productId))
                }
                .map { pricedProductId }
            }
        }
      }.blockingGet()
  }

  @Delete()
  fun delete(roomPricedProduct: RoomPricedProduct): Completable

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(productCategoryCrossRef: PricedProductCategoryCrossRef): Single<Long>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(pricedProductProductCrossRef: PricedProductProductCrossRef): Single<Long>
}
