package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.data.model.place.RoomPlaceWithPlaceProducts

@Dao
interface PlaceDao {
  @Query("SELECT MAX(placeId) FROM place_table")
  fun getLastId(): Maybe<Long>

  @Transaction
  @Query("select * from place_table")
  fun getAll(): Single<List<RoomPlaceWithPlaceProducts>>

  @Transaction
  @Query("select * from place_table")
  fun observeAll(): Observable<List<RoomPlaceWithPlaceProducts>>

  @Transaction
  @Query("select * from place_table where placeId=:placeId")
  fun getPlace(placeId: Long): Maybe<RoomPlaceWithPlaceProducts>

  @Transaction
  @Query("select * from place_table where nameLowercase=:placeNameLowercase")
  fun getPlace(placeNameLowercase: String): Maybe<RoomPlaceWithPlaceProducts>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(roomPlace: RoomPlace): Single<Long>

  @Delete()
  fun delete(roomPlace: RoomPlace): Completable

  @Query("select * from placeplaceproductcrossref where placeId=:placeId")
  fun getAllCrossRef(placeId: Long): Single<List<PlacePlaceProductCrossRef>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(placePlaceProductCrossRef: PlacePlaceProductCrossRef): Single<Long>

  @Delete()
  fun delete(placePlaceProductCrossRef: PlacePlaceProductCrossRef): Completable
}
