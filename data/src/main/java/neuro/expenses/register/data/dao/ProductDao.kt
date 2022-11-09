package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.RoomPricedProduct
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.data.model.product.PricedProductCategoryCrossRef
import neuro.expenses.register.data.model.product.PricedProductProductCrossRef
import neuro.expenses.register.data.model.product.RoomPricedProductWithProductAndCategory

@Dao
interface ProductDao {
  @Query("select * from product_table where productId=:productId")
  fun getProduct(productId: Long): Maybe<RoomProduct>

  @Query("select * from product_table where description=:description")
  fun getProduct(description: String): Maybe<RoomProduct>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(roomProduct: RoomProduct): Single<Long>

  @Update()
  fun update(roomProduct: RoomProduct): Completable

  @Delete()
  fun delete(roomProduct: RoomProduct): Completable

  @Query("select * from priced_product_table where pricedProductId=:pricedProductId")
  fun getPricedProduct(pricedProductId: Long): Maybe<RoomPricedProductWithProductAndCategory>

  @Query("select * from priced_product_table where productId=:productId and category=:category and price=:price")
  fun getPricedProduct(productId: Long, category: String, price: Double): Maybe<RoomPricedProduct>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(roomPricedProduct: RoomPricedProduct): Single<Long>

  /**
   * Insert a RoomProduct, RoomPricedProduct, PricedProductCategoryCrossRef and PricedProductProductCrossRef if needed.
   *
   * If any of the referred entities already exists in the database, it will not be rewritten.
   *
   * @param defaultAmount default amount to be used when creating the RoomPricedProduct.
   * @param iconUrl iconUrl to be used when creating the RoomProduct.
   *
   * **Both these parameters will be ignored in case the respective entities already exist in the
   * database. As such this shall not be used to update those attributes.**
   *
   * @return pricedProductId.
   */
  @Transaction
  fun insert(
    description: String,
    category: String,
    price: Double,
    defaultAmount: Double,
    iconUrl: String = ""
  ): Long {
    return getProduct(description).defaultIfEmpty(RoomProduct(0, description, iconUrl))
      .flatMap { roomProduct ->
        insert(roomProduct).flatMap innerFlatMap@{ productId ->
          if (productId == -1L) {
            return@innerFlatMap getProduct(description).map { it.productId }.toSingle()
          } else {
            return@innerFlatMap Single.just(productId)
          }
        }.flatMap { productId ->
          getPricedProduct(productId, category, price).defaultIfEmpty(
            RoomPricedProduct(
              productId,
              category,
              price,
              defaultAmount
            )
          ).flatMap { roomPricedProduct ->
            insert(roomPricedProduct)
          }.flatMap {
            if (it == -1L) getPricedProduct(
              roomProduct.productId,
              category,
              price
            ).map { it.pricedProductId }.toSingle() else Single.just(it)
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

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(productCategoryCrossRef: PricedProductCategoryCrossRef): Single<Long>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(pricedProductProductCrossRef: PricedProductProductCrossRef): Single<Long>
}
