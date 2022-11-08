package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.place.PlacePricedProductCrossRef
import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.data.model.place.RoomPlaceWithPricedProducts

@Dao
interface PlaceDao {
  @Transaction
  @Query("select * from place_table")
  fun getAll(): Single<List<RoomPlaceWithPricedProducts>>

  @Transaction
  @Query("select * from place_table")
  fun observeAll(): Observable<List<RoomPlaceWithPricedProducts>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(roomPlace: RoomPlace): Single<Long>

  @Delete()
  fun delete(roomPlace: RoomPlace): Completable

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(placePricedProductCrossRef: PlacePricedProductCrossRef): Single<Long>

  @Delete()
  fun delete(placePricedProductCrossRef: PlacePricedProductCrossRef): Completable
}
