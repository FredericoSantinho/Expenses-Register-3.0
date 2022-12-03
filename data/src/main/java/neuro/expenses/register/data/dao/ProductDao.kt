package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.product.RoomProduct

@Dao
interface ProductDao {
  @Query("SELECT MAX(productId) FROM product_table")
  fun getLastProductId(): Maybe<Long>

  @Query("select * from product_table where productId=:productId")
  fun getProduct(productId: Long): Maybe<RoomProduct>

  @Query("select * from product_table where descriptionLowercase=:descriptionLowercase")
  fun getProduct(descriptionLowercase: String): Maybe<RoomProduct>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(roomProduct: RoomProduct): Single<Long>

  @Update()
  fun update(roomProduct: RoomProduct): Completable

  @Delete()
  fun delete(roomProduct: RoomProduct): Completable
}
