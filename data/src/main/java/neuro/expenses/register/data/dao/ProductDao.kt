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
  @Query("SELECT MAX(productId) FROM product_table")
  fun getLastProductId(): Maybe<Long>

  @Query("select * from product_table where productId=:productId")
  fun getProduct(productId: Long): Maybe<RoomProduct>

  @Query("select * from product_table where descriptionLowercase=:descriptionLowercase")
  fun getProduct(descriptionLowercase: String): Maybe<RoomProduct>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(roomProduct: RoomProduct): Single<Long>

  @Update()
  fun update(roomProduct: RoomProduct): Completable

  @Delete()
  fun delete(roomProduct: RoomProduct): Completable

  @Query("SELECT MAX(placeProductId) FROM place_product_table")
  fun getLastPlaceProductId(): Maybe<Long>

  @Transaction
  @Query("select * from place_product_table where placeProductId=:placeProductId")
  fun getPlaceProduct(placeProductId: Long): Maybe<RoomPlaceProductWithProductAndCategory>

  @Query("select * from place_product_table where productId=:productId and categoryId=:categoryId and price=:price")
  fun getPlaceProduct(productId: Long, categoryId: Long, price: Double): Maybe<RoomPlaceProduct>

  @Query("select * from place_product_table where productId=:productId and categoryId=:categoryId and price=:price and placeId=:placeId")
  fun getPlaceProductWithProductAndCategory(
    productId: Long, categoryId: Long, price: Double, placeId: Long
  ): Maybe<RoomPlaceProductWithProductAndCategory>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
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

  /**
   * Insert a RoomProduct, RoomPlaceProduct, PlaceProductCategoryCrossRef and PlaceProductProductCrossRef if needed.
   *
   * If any of the referred entities already exists in the database, it will not be rewritten.
   *
   * **Both these parameters will be ignored in case the respective entities already exist in the
   * database. As such this shall not be used to update those attributes.**
   *
   * @return placeProductId.
   */
  @Transaction
  @Deprecated("only to keep legacy database pre populate")
  fun insert(
    placeProductId: Long,
    description: String,
    categoryId: Long,
    price: Double,
    iconUrl: String = "",
    variableAmount: Boolean,
    placeId: Long
  ): Long {
    return getProduct(description.lowercase()).defaultIfEmpty(
      RoomProduct(
        0, description, iconUrl, variableAmount
      )
    ).flatMap { roomProduct ->
      insert(roomProduct).flatMap { productId ->
        if (productId == -1L) {
          getProduct(description.lowercase()).map { it.productId }.toSingle()
        } else {
          Single.just(productId)
        }
      }.flatMap { productId ->
        getPlaceProduct(productId, categoryId, price).defaultIfEmpty(
          RoomPlaceProduct(placeProductId, productId, categoryId, price, placeId)
        ).map { roomPlaceProduct ->
          insert(roomPlaceProduct)
        }.flatMap {
          if (it == -1L) getPlaceProduct(
            roomProduct.productId, categoryId, price
          ).map { it.placeProductId }.toSingle() else Single.just(it)
        }.map { placeProductId ->
          insert(PlaceProductCategoryCrossRef(placeProductId, categoryId))
          insert(PlaceProductProductCrossRef(placeProductId, productId))
          placeProductId
        }
      }
    }.blockingGet()
  }

  @Delete()
  fun delete(roomPlaceProduct: RoomPlaceProduct): Completable

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(productCategoryCrossRef: PlaceProductCategoryCrossRef): Long

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(placeProductProductCrossRef: PlaceProductProductCrossRef): Long
}
