package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.RoomProduct
import neuro.expenses.register.data.model.product.PlaceProductCategoryCrossRef
import neuro.expenses.register.data.model.product.PlaceProductProductCrossRef
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory

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

  @Transaction
  @Query("select * from place_product_table where placeProductId=:placeProductId")
  fun getPlaceProduct(placeProductId: Long): Maybe<RoomPlaceProductWithProductAndCategory>

  @Query("select * from place_product_table where productId=:productId and categoryId=:categoryId and price=:price")
  fun getPlaceProduct(productId: Long, categoryId: Long, price: Double): Maybe<RoomPlaceProduct>

  @Query("select * from place_product_table where productId=:productId and categoryId=:categoryId and price=:price")
  fun getPlaceProductWithProductAndCategory(
    productId: Long,
    categoryId: Long,
    price: Double
  ): Maybe<RoomPlaceProductWithProductAndCategory>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(roomPlaceProduct: RoomPlaceProduct): Single<Long>

  /**
   * Insert a RoomProduct, RoomPlaceProduct, PlaceProductCategoryCrossRef and PlaceProductProductCrossRef if needed.
   *
   * If any of the referred entities already exists in the database, it will not be rewritten.
   *
   * @param defaultAmount default amount to be used when creating the RoomPlaceProduct.
   * @param iconUrl iconUrl to be used when creating the RoomProduct.
   *
   * **Both these parameters will be ignored in case the respective entities already exist in the
   * database. As such this shall not be used to update those attributes.**
   *
   * @return placeProductId.
   */
  @Transaction
  fun insert(
    description: String,
    categoryId: Long,
    price: Double,
    defaultAmount: Double,
    iconUrl: String = ""
  ): Long {
    return getProduct(description).defaultIfEmpty(RoomProduct(0, description, iconUrl))
      .flatMap { roomProduct ->
        insert(roomProduct).flatMap { productId ->
          if (productId == -1L) {
            getProduct(description).map { it.productId }.toSingle()
          } else {
            Single.just(productId)
          }
        }.flatMap { productId ->
          getPlaceProduct(productId, categoryId, price).defaultIfEmpty(
            RoomPlaceProduct(
              productId,
              categoryId,
              price,
              defaultAmount
            )
          ).flatMap { roomPlaceProduct ->
            insert(roomPlaceProduct)
          }.flatMap {
            if (it == -1L) getPlaceProduct(
              roomProduct.productId,
              categoryId,
              price
            ).map { it.placeProductId }.toSingle() else Single.just(it)
          }
            .flatMap { placeProductId ->
              insert(PlaceProductCategoryCrossRef(placeProductId, categoryId))
                .flatMap {
                  insert(PlaceProductProductCrossRef(placeProductId, productId))
                }
                .map { placeProductId }
            }
        }
      }.blockingGet()
  }

  @Delete()
  fun delete(roomPlaceProduct: RoomPlaceProduct): Completable

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(productCategoryCrossRef: PlaceProductCategoryCrossRef): Single<Long>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(placeProductProductCrossRef: PlaceProductProductCrossRef): Single<Long>
}
