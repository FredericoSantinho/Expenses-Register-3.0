package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.category.RoomCategory

@Dao
interface CategoryDao {
  @Query("SELECT MAX(categoryId) FROM category_table")
  fun getLastCategoryId(): Maybe<Long>

  @Query("select * from category_table")
  fun observeCategories(): Observable<List<RoomCategory>>

  @Query("select * from category_table where nameLowercase=:nameLowercase")
  fun getCategory(nameLowercase: String): Maybe<RoomCategory>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(category: RoomCategory): Single<Long>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(categories: List<RoomCategory>): Single<List<Long>>

  @Update()
  fun update(category: RoomCategory): Completable

  @Query("delete from category_table where categoryId=:categoryId")
  fun delete(categoryId: Long): Completable
}
