package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.model.RoomCategory

@Dao
interface CategoryDao {
  @Query("select * from category_table")
  fun getCategories(): Observable<List<RoomCategory>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(category: RoomCategory)

  @Delete()
  fun delete(category: RoomCategory)
}
