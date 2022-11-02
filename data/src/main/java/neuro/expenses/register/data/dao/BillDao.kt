package neuro.expenses.register.data.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.model.bill.*

@Dao
interface BillDao {
  @Transaction
  @Query("select * from bill_table order by billId desc limit 1")
  fun observeLastBill(): Observable<RoomBillWithBillItems>

  @Transaction
  @Query("select * from bill_table order by billId asc")
  fun observeBills(): Observable<List<RoomBillWithBillItems>>

  @Transaction
  fun insert(roomBill: RoomBill, roomBillItem: RoomBillItem) {
    insert(roomBill).flatMap { billId ->
      insert(roomBillItem).flatMap { billItemId ->
        insert(
          BillItemPricedProductCrossRef(
            billItemId,
            roomBillItem.pricedProductId
          )
        )
      }
    }.blockingGet()
  }

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(roomBill: RoomBill): Single<Long>

  @Update()
  fun update(roomBill: RoomBill): Completable

  @Delete()
  fun delete(roomBill: RoomBill): Completable

  @Transaction
  @Query("select * from bill_item_table where billItemId=:billItemId")
  fun getBillItem(billItemId: Long): Single<RoomBillItem>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(roomBillItem: RoomBillItem): Single<Long>

  @Update()
  fun update(roomBillItem: RoomBillItem): Completable

  @Delete()
  fun delete(roomBillItem: RoomBillItem): Completable

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insert(pricedProductCrossRef: BillItemPricedProductCrossRef): Single<Long>
}
